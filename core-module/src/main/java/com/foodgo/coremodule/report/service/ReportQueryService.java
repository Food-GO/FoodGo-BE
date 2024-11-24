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

    public ReportComparisonDTO getWeeklyReportComparison(Long userId) {
        LocalDate today = LocalDate.now();

        // 이번 주와 저번 주의 날짜 범위 계산
        LocalDate startOfThisWeek = getStartOfWeek(today);
        LocalDate endOfThisWeek = getEndOfWeek(today);
        LocalDate startOfLastWeek = startOfThisWeek.minusWeeks(1);
        LocalDate endOfLastWeek = endOfThisWeek.minusWeeks(1);

        // 이번 주와 저번 주의 칼로리 합산
        double thisWeekTotal = calculateTotalForWeek(userId, startOfThisWeek, endOfThisWeek);
        double lastWeekTotal = calculateTotalForWeek(userId, startOfLastWeek, endOfLastWeek);

        // 영양소별 합산 (탄수화물, 단백질, 지방, 당류, 나트륨 등)
        double thisWeekCarbs = calculateCarbsForWeek(userId, startOfThisWeek, endOfThisWeek);
        double lastWeekCarbs = calculateCarbsForWeek(userId, startOfLastWeek, endOfLastWeek);

        double thisWeekProteins = calculateProteinsForWeek(userId, startOfThisWeek, endOfThisWeek);
        double lastWeekProteins = calculateProteinsForWeek(userId, startOfLastWeek, endOfLastWeek);

        double thisWeekFats = calculateFatsForWeek(userId, startOfThisWeek, endOfThisWeek);
        double lastWeekFats = calculateFatsForWeek(userId, startOfLastWeek, endOfLastWeek);

        double thisWeekSugar = calculateSugarForWeek(userId, startOfThisWeek, endOfThisWeek);
        double lastWeekSugar = calculateSugarForWeek(userId, startOfLastWeek, endOfLastWeek);

        double thisWeekSodium = calculateSodiumForWeek(userId, startOfThisWeek, endOfThisWeek);
        double lastWeekSodium = calculateSodiumForWeek(userId, startOfLastWeek, endOfLastWeek);

        double thisWeekCholesterol = calculateCholesterolForWeek(userId, startOfThisWeek, endOfThisWeek);
        double lastWeekCholesterol = calculateCholesterolForWeek(userId, startOfLastWeek, endOfLastWeek);

        double thisWeekSaturatedFat = calculateSaturatedFatForWeek(userId, startOfThisWeek, endOfThisWeek);
        double lastWeekSaturatedFat = calculateSaturatedFatForWeek(userId, startOfLastWeek, endOfLastWeek);

        double thisWeekTransFat = calculateTransFatForWeek(userId, startOfThisWeek, endOfThisWeek);
        double lastWeekTransFat = calculateTransFatForWeek(userId, startOfLastWeek, endOfLastWeek);

        // 결과 반환
        return new ReportComparisonDTO(
                lastWeekTotal, thisWeekTotal,
                lastWeekCarbs, thisWeekCarbs,
                lastWeekProteins, thisWeekProteins,
                lastWeekFats, thisWeekFats,
                lastWeekSugar, thisWeekSugar,
                lastWeekSodium, thisWeekSodium,
                lastWeekCholesterol, thisWeekCholesterol,
                lastWeekSaturatedFat, thisWeekSaturatedFat,
                lastWeekTransFat, thisWeekTransFat
        );
    }

    // 주간 칼로리 합계 계산
    private double calculateTotalForWeek(Long userId, LocalDate start, LocalDate end) {
        List<Report> reports = reportRepository.findReportsByUserAndCreatedAtBetween(userId, start.atStartOfDay(), end.atTime(23, 59, 59));
        return reports.stream().mapToDouble(Report::getTotal).sum();
    }

    // 주간 탄수화물 합계 계산
    private double calculateCarbsForWeek(Long userId, LocalDate start, LocalDate end) {
        List<Report> reports = reportRepository.findReportsByUserAndCreatedAtBetween(userId, start.atStartOfDay(), end.atTime(23, 59, 59));
        return reports.stream().mapToDouble(Report::getCarb).sum();
    }

    // 주간 단백질 합계 계산
    private double calculateProteinsForWeek(Long userId, LocalDate start, LocalDate end) {
        List<Report> reports = reportRepository.findReportsByUserAndCreatedAtBetween(userId, start.atStartOfDay(), end.atTime(23, 59, 59));
        return reports.stream().mapToDouble(Report::getProtein).sum();
    }

    // 주간 지방 합계 계산
    private double calculateFatsForWeek(Long userId, LocalDate start, LocalDate end) {
        List<Report> reports = reportRepository.findReportsByUserAndCreatedAtBetween(userId, start.atStartOfDay(), end.atTime(23, 59, 59));
        return reports.stream().mapToDouble(Report::getFat).sum();
    }

    // 주간 당류 합계 계산
    private double calculateSugarForWeek(Long userId, LocalDate start, LocalDate end) {
        List<Report> reports = reportRepository.findReportsByUserAndCreatedAtBetween(userId, start.atStartOfDay(), end.atTime(23, 59, 59));
        return reports.stream().mapToDouble(Report::getSugar).sum();
    }

    // 주간 나트륨 합계 계산
    private double calculateSodiumForWeek(Long userId, LocalDate start, LocalDate end) {
        List<Report> reports = reportRepository.findReportsByUserAndCreatedAtBetween(userId, start.atStartOfDay(), end.atTime(23, 59, 59));
        return reports.stream().mapToDouble(Report::getSodium).sum();
    }

    // 주간 콜레스테롤 합계 계산
    private double calculateCholesterolForWeek(Long userId, LocalDate start, LocalDate end) {
        List<Report> reports = reportRepository.findReportsByUserAndCreatedAtBetween(userId, start.atStartOfDay(), end.atTime(23, 59, 59));
        return reports.stream().mapToDouble(Report::getCholesterol).sum();
    }

    // 주간 포화지방산 합계 계산
    private double calculateSaturatedFatForWeek(Long userId, LocalDate start, LocalDate end) {
        List<Report> reports = reportRepository.findReportsByUserAndCreatedAtBetween(userId, start.atStartOfDay(), end.atTime(23, 59, 59));
        return reports.stream().mapToDouble(Report::getSaturatedFat).sum();
    }

    // 주간 트랜스지방 합계 계산
    private double calculateTransFatForWeek(Long userId, LocalDate start, LocalDate end) {
        List<Report> reports = reportRepository.findReportsByUserAndCreatedAtBetween(userId, start.atStartOfDay(), end.atTime(23, 59, 59));
        return reports.stream().mapToDouble(Report::getTransFat).sum();
    }
}
