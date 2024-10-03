package com.foodgo.apimodule.ingredient.presentation;

import java.net.URISyntaxException;

import com.foodgo.apimodule.ingredient.application.IngredientFindUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodgo.commonmodule.common.ApplicationResponse;
import com.foodgo.coremodule.ingredient.dto.request.IngredientGetRequest;
import com.foodgo.coremodule.ingredient.dto.response.IngredientGetResponse;
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

	private final IngredientFindUseCase ingredientFindUseCase;

	@PostMapping("")
	public ApplicationResponse<IngredientGetResponse.Row> getIngredientInfo(
		@UserResolver User user,
		@RequestBody @Valid IngredientGetRequest request
	) throws URISyntaxException {
		return ApplicationResponse.onSuccess(ingredientFindUseCase.getIngredient(user, request));
	}
}
