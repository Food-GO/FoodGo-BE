package com.foodgo.coremodule.report.service;

import com.foodgo.coremodule.report.ReportRepository;
import com.foodgo.coremodule.report.domain.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportQueryService {

    private final ReportRepository reportRepository;

    public List<Report> findReportByDate(Long userId, LocalDateTime start, LocalDateTime end) {
        return reportRepository.findAllByUserIdAndCreatedAtBetween(userId, start, end);
    }
}
