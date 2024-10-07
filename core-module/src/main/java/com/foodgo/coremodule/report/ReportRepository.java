package com.foodgo.coremodule.report;

import com.foodgo.coremodule.report.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAllByUserIdAndCreatedAtBetween(Long userId, LocalDateTime start, LocalDateTime end);

    // 이번 주 총 레포트 조회
    @Query("SELECT r FROM Report r WHERE r.user.id = :userId AND r.createdAt >= :startOfWeek AND r.createdAt < :endOfWeek")
    List<Report> findReportsByUserAndWeek(Long userId, LocalDate startOfWeek, LocalDate endOfWeek);

}
