package com.foodgo.coremodule.report.dto;

public record ReportComparisonDTO(
        int lastWeekTotal,
        int thisWeekTotal,
        int lastWeekCarbs,
        int thisWeekCarbs,
        int lastWeekProteins,
        int thisWeekProteins,
        int lastWeekFats,
        int thisWeekFats
) {
}
