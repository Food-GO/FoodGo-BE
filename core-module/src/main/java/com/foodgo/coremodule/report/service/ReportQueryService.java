package com.foodgo.coremodule.report.service;

import com.foodgo.coremodule.report.ReportRepository;
import com.foodgo.coremodule.report.domain.Report;
import com.foodgo.coremodule.report.dto.ReportComparisonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportQueryService {

    private final ReportRepository reportRepository;

    public List<Report> findReportByDate(Long userId, LocalDateTime start, LocalDateTime end) {
        return reportRepository.findAllByUserIdAndCreatedAtBetween(userId, start, end);
    }

    public void saveReport(Report report) {
        reportRepository.save(report);
    }

    // 이번 주 시작과 끝 날짜 구하기
    private LocalDate getStartOfWeek(LocalDate date) {
        return date.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
    }

    private LocalDate getEndOfWeek(LocalDate date) {
        return date.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));
    }

    // 이번 주와 저번 주의 칼로리 리포트 비교
    public ReportComparisonDTO getWeeklyReportComparison(Long userId) {
        LocalDate today = LocalDate.now();

        // 이번 주와 저번 주의 날짜 범위 계산
        LocalDate startOfThisWeek = getStartOfWeek(today);
        LocalDate endOfThisWeek = getEndOfWeek(today);
        LocalDate startOfLastWeek = startOfThisWeek.minusWeeks(1);
        LocalDate endOfLastWeek = endOfThisWeek.minusWeeks(1);

        // 이번 주와 저번 주 레포트 가져오기
        List<Report> thisWeekReports = reportRepository.findReportsByUserAndWeek(userId, startOfThisWeek, endOfThisWeek);
        List<Report> lastWeekReports = reportRepository.findReportsByUserAndWeek(userId, startOfLastWeek, endOfLastWeek);

        // 칼로리 및 영양소 총합 계산
        int thisWeekTotal = calculateTotalCalories(thisWeekReports);
        int lastWeekTotal = calculateTotalCalories(lastWeekReports);

        int thisWeekCarbs = calculateTotalCarbs(thisWeekReports);
        int lastWeekCarbs = calculateTotalCarbs(lastWeekReports);

        int thisWeekProteins = calculateTotalProteins(thisWeekReports);
        int lastWeekProteins = calculateTotalProteins(lastWeekReports);

        int thisWeekFats = calculateTotalFats(thisWeekReports);
        int lastWeekFats = calculateTotalFats(lastWeekReports);

        // 결과 반환
        return new ReportComparisonDTO(
                lastWeekTotal, thisWeekTotal,
                lastWeekCarbs, thisWeekCarbs,
                lastWeekProteins, thisWeekProteins,
                lastWeekFats, thisWeekFats
        );
    }

    // 칼로리 합계 계산
    private int calculateTotalCalories(List<Report> reports) {
        return reports.stream().mapToInt(Report::getTotal).sum();
    }

    // 탄수화물 합계 계산
    private int calculateTotalCarbs(List<Report> reports) {
        return reports.stream().mapToInt(Report::getCarb).sum();
    }

    // 단백질 합계 계산
    private int calculateTotalProteins(List<Report> reports) {
        return reports.stream().mapToInt(Report::getProtein).sum();
    }

    // 지방 합계 계산
    private int calculateTotalFats(List<Report> reports) {
        return reports.stream().mapToInt(Report::getFat).sum();
    }
}
