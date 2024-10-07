package com.foodgo.apimodule.report.mapper;

import com.foodgo.coremodule.ingredient.dto.response.IngredientGetResponse;
import com.foodgo.coremodule.report.domain.Report;
import com.foodgo.coremodule.user.domain.User;

public class ReportMapper {

    public static Report mapToReport(User user, IngredientGetResponse.Row row) {
        return Report.builder()
                .user(user)
                .total(Integer.parseInt(row.getNutrCont1()))  // 총 칼로리
                .carb(Integer.parseInt(row.getNutrCont2()))   // 탄수화물
                .protein(Integer.parseInt(row.getNutrCont3())) // 단백질
                .fat(Integer.parseInt(row.getNutrCont4()))    // 지방
                .sugar(Integer.parseInt(row.getNutrCont5()))  // 당류
                .sodium(Integer.parseInt(row.getNutrCont6())) // 나트륨
                .cholesterol(Integer.parseInt(row.getNutrCont7())) // 콜레스테롤
                .saturatedFat(Integer.parseInt(row.getNutrCont8())) // 포화지방산
                .transFat(Integer.parseInt(row.getNutrCont9())) // 트랜스지방
                .build();
    }
}
