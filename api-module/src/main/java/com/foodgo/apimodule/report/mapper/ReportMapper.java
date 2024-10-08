package com.foodgo.apimodule.report.mapper;

import com.foodgo.coremodule.ingredient.dto.response.IngredientGetResponse;
import com.foodgo.coremodule.report.domain.MealType;
import com.foodgo.coremodule.report.domain.Report;
import com.foodgo.coremodule.user.domain.User;

import java.time.LocalTime;

public class ReportMapper {

    public static Report mapToReport(User user, IngredientGetResponse.Row row) {
        MealType mealType = determineMealType(); // MealType을 결정하는 메서드 호출

        return Report.builder()
                .user(user)
                .type(mealType) // MealType 저장
                .total(Double.parseDouble(row.getNutrCont1()))  // 총 칼로리
                .carb(Double.parseDouble(row.getNutrCont2()))   // 탄수화물
                .protein(Double.parseDouble(row.getNutrCont3())) // 단백질
                .fat(Double.parseDouble(row.getNutrCont4()))    // 지방
                .sugar(Double.parseDouble(row.getNutrCont5()))  // 당류
                .sodium(Double.parseDouble(row.getNutrCont6())) // 나트륨
                .cholesterol(Double.parseDouble(row.getNutrCont7())) // 콜레스테롤
                .saturatedFat(Double.parseDouble(row.getNutrCont8())) // 포화지방산
                .transFat(Double.parseDouble(row.getNutrCont9())) // 트랜스지방
                .build();
    }

    private static MealType determineMealType() {
        LocalTime now = LocalTime.now();

        if (now.isBefore(LocalTime.NOON)) {
            return MealType.BREAKFAST; // 정오 이전이면 아침
        } else if (now.isBefore(LocalTime.of(15, 0))) {
            return MealType.LUNCH; // 오후 3시 이전이면 점심
        } else {
            return MealType.DINNER; // 그 이후는 저녁
        }
    }
}
