package com.foodgo.apimodule.community.application;

import com.foodgo.apimodule.community.dto.ChallengeList;
import com.foodgo.apimodule.community.mapper.ChallengeMapper;
import com.foodgo.coremodule.community.domain.Challenge;
import com.foodgo.coremodule.community.domain.ChallengeType;
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

        // 챌린지 타입에 맞게 리포트 달성률 계산
        double myRate = 0.0;
        double friendRate = 0.0;

        if (challenge.getType() == ChallengeType.CALORIE) {

            int totalSum = myReports.stream()
                    .mapToInt(Report::getTotal)
                    .sum();
            int friendSum = friendReports.stream()
                    .mapToInt(Report::getTotal)
                    .sum();
            myRate = (double) totalSum / challenge.getValue();
            friendRate = (double) friendSum / challenge.getValue();
        } else if (challenge.getType() == ChallengeType.CARB) {

            int totalSum = myReports.stream()
                    .mapToInt(Report::getCarb)
                    .sum();
            int friendSum = friendReports.stream()
                    .mapToInt(Report::getCarb)
                    .sum();
            myRate = (double) totalSum / challenge.getValue();
            friendRate = (double) friendSum / challenge.getValue();
        } else if (challenge.getType() == ChallengeType.PROTEIN) {

            int totalSum = myReports.stream()
                    .mapToInt(Report::getProtein)
                    .sum();
            int friendSum = friendReports.stream()
                    .mapToInt(Report::getProtein)
                    .sum();
            myRate = (double) totalSum / challenge.getValue();
            friendRate = (double) friendSum / challenge.getValue();
        } else if (challenge.getType() == ChallengeType.FAT) {

            int totalSum = myReports.stream()
                    .mapToInt(Report::getFat)
                    .sum();
            int friendSum = friendReports.stream()
                    .mapToInt(Report::getFat)
                    .sum();
            myRate = (double) totalSum / challenge.getValue();
            friendRate = (double) friendSum / challenge.getValue();
        } else if (challenge.getType() == ChallengeType.FREQUENCY) {

            int totalSum = myReports.size();
            int friendSum = friendReports.size();
            myRate = (double) totalSum / challenge.getValue();
            friendRate = (double) friendSum / challenge.getValue();
        }

        return ChallengeMapper.toDto(challenge, myRate, friendRate);
    }

    private LocalDate toLocalDate(Integer year, Integer month, Integer date) {
        return LocalDate.of(year, month, date);
    }
}
