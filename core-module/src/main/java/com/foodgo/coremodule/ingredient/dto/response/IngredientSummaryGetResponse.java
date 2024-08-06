package com.foodgo.coremodule.ingredient.dto.response;

import com.foodgo.coremodule.ingredient.entity.Ingredient;
import com.foodgo.coremodule.user.domain.User;
import com.foodgo.coremodule.user.dto.response.UserDetailGetResponse;
import com.foodgo.coremodule.user.enums.DiseaseType;
import com.foodgo.coremodule.user.enums.UsageType;

import lombok.Builder;

@Builder
public record IngredientSummaryGetResponse(
	Long id,
	String ingredientName,
	double nutrCont1,
	double nutrCont2,
	double nutrCont3,
	double nutrCont4

) {

	public static IngredientSummaryGetResponse from(Ingredient ingredient) {
		return IngredientSummaryGetResponse.builder()
			.id(ingredient.getId())
			.ingredientName(ingredient.getIngredientName())
			.nutrCont1(ingredient.getNutrCont1())
			.nutrCont2(ingredient.getNutrCont2())
			.nutrCont3(ingredient.getNutrCont3())
			.build();
	}
}
