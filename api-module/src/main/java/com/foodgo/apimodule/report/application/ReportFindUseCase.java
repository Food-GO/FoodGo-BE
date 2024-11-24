package com.foodgo.apimodule.report.application;

import com.foodgo.coremodule.report.dto.ReportComparisonDTO;
import com.foodgo.coremodule.report.service.ReportQueryService;
import com.foodgo.coremodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportFindUseCase {

    private final ReportQueryService reportQueryService;

    // 통게 dto list 만들기
    public ReportComparisonDTO getStatistics(User user) {
        return reportQueryService.getWeeklyReportComparison(user.getId());
    }
}
