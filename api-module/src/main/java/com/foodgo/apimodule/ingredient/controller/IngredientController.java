package com.foodgo.apimodule.ingredient.controller;

import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodgo.commonmodule.common.ApiResponse;
import com.foodgo.coremodule.ingredient.dto.request.IngredientGetRequest;
import com.foodgo.coremodule.ingredient.dto.response.IngredientGetResponse;
import com.foodgo.coremodule.ingredient.service.IngredientService;
import com.foodgo.coremodule.security.annotation.UserResolver;
import com.foodgo.coremodule.user.domain.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ingredients")
public class IngredientController {

	private final IngredientService ingredientService;

	@PostMapping("")
	public ApiResponse<IngredientGetResponse.Row> getIngredientInfo(
		@UserResolver User user,
		@RequestBody @Valid IngredientGetRequest request
	) throws URISyntaxException {
		return ApiResponse.onSuccess(ingredientService.getIngredient(user, request));
	}
}
