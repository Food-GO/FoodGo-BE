package com.foodgo.coremodule.report.dto;

public record ReportComparisonDTO(
        Double lastWeekTotal,
        Double thisWeekTotal,
        Double lastWeekCarbs,
        Double thisWeekCarbs,
        Double lastWeekProteins,
        Double thisWeekProteins,
        Double lastWeekFats,
        Double thisWeekFats,
        Double lastWeekSugar,
        Double thisWeekSugar,
        Double lastWeekSodium,
        Double thisWeekSodium,
        Double lastWeekCholesterol,
        Double thisWeekCholesterol,
        Double lastWeekSaturatedFat,
        Double thisWeekSaturatedFat,
        Double lastWeekTransFat,
        Double thisWeekTransFat
) {
}
