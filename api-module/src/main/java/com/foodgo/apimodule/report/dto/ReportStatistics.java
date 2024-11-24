package com.foodgo.apimodule.report.dto;

public record ReportStatistics(
        String name,
        Double lastweek,
        Double thisweek
) {
}
