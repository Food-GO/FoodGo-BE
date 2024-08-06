package com.foodgo.apimodule.ingredient.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodgo.commonmodule.common.ApiResponse;
import com.foodgo.coremodule.ingredient.dto.response.IngredientSummaryGetResponse;
import com.foodgo.coremodule.ingredient.service.IngredientQueryService;
import com.foodgo.coremodule.ingredient.service.IngredientService;
import com.foodgo.coremodule.security.annotation.UserResolver;
import com.foodgo.coremodule.user.domain.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ingredients")
public class IngredientController {

	private final IngredientService ingredientService;
	private final IngredientQueryService ingredientQueryService;

	@GetMapping("/summary")
	public ApiResponse<IngredientSummaryGetResponse> getIngredientInfo(
		@UserResolver User user,
		@RequestParam String name
	) {
		return ApiResponse.onSuccess(ingredientQueryService.getSummaryIngredient(user, name));
	}
}
