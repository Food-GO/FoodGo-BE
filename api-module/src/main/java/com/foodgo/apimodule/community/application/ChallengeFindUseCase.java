package com.foodgo.apimodule.community.application;

import com.foodgo.apimodule.community.dto.ChallengeList;
import com.foodgo.apimodule.community.mapper.ChallengeMapper;
import com.foodgo.coremodule.community.domain.Challenge;
import com.foodgo.coremodule.community.exception.ChallengeErrorCode;
import com.foodgo.coremodule.community.exception.ChallengeException;
import com.foodgo.coremodule.community.service.ChallengeQueryService;
import com.foodgo.coremodule.report.domain.Report;
import com.foodgo.coremodule.report.service.ReportQueryService;
import com.foodgo.coremodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChallengeFindUseCase {

    private final ChallengeQueryService challengeQueryService;
    private final ReportQueryService reportQueryService;

    public ChallengeList findChallengeList(User user, Long challengeId) {

        Challenge challenge = challengeQueryService.findChallengeById(challengeId);

        LocalDateTime startDate = challenge.getCreatedAt();
        LocalDate endDate = toLocalDate(challenge.getYear(), challenge.getMonth(), challenge.getDate());
        LocalDateTime endDateTimeEndOfDay = endDate.atTime(LocalTime.MAX);

        List<Report> myReports = reportQueryService.findReportByDate(
                user.getId(), startDate, endDateTimeEndOfDay);
        List<Report> friendReports = reportQueryService.findReportByDate(
                challenge.getFriendship().getFriend().getId(), startDate, endDateTimeEndOfDay);

        // 챌린지 타입에 따른 달성률 계산
        double myRate = calculateRate(myReports, challenge);
        double friendRate = calculateRate(friendReports, challenge);

        return ChallengeMapper.toDto(challenge, myRate, friendRate);
    }

    private double calculateRate(List<Report> reports, Challenge challenge) {
        int totalSum = 0;

        switch (challenge.getType()) {
            case CALORIE:
                totalSum = reports.stream().mapToInt(Report::getTotal).sum();
                break;
            case CARB:
                totalSum = reports.stream().mapToInt(Report::getCarb).sum();
                break;
            case PROTEIN:
                totalSum = reports.stream().mapToInt(Report::getProtein).sum();
                break;
            case FAT:
                totalSum = reports.stream().mapToInt(Report::getFat).sum();
                break;
            case FREQUENCY:
                totalSum = reports.size();
                break;
            default:
                throw new ChallengeException(ChallengeErrorCode.NO_CHALLENGE_TYPE);
        }

        return (double) totalSum / challenge.getValue();
    }

    private LocalDate toLocalDate(Integer year, Integer month, Integer date) {
        return LocalDate.of(year, month, date);
    }

}
