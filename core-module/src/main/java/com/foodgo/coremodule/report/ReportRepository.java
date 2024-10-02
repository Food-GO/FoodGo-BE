package com.foodgo.coremodule.report;

import com.foodgo.coremodule.report.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAllByUserIdAndCreatedAtBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
