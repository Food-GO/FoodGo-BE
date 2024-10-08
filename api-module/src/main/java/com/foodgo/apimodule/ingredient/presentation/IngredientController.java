package com.foodgo.apimodule.ingredient.presentation;

import com.foodgo.apimodule.ingredient.application.IngredientFindUseCase;
import com.foodgo.commonmodule.common.ApplicationResponse;
import com.foodgo.coremodule.ingredient.dto.request.IngredientGetRequest;
import com.foodgo.coremodule.ingredient.dto.response.IngredientGetResponse;
import com.foodgo.coremodule.security.annotation.UserResolver;
import com.foodgo.coremodule.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ingredients")
@Tag(name = "ingredient", description = "식재료 관련 API")
public class IngredientController {

    private final IngredientFindUseCase ingredientFindUseCase;

    @PostMapping("")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "식재료 정보 확인 성공",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "식재료 정보 확인 API", description = "식재료 정보 확인 + 리포트 DB 저장 API 입니다.")
    public ApplicationResponse<IngredientGetResponse.Row> getIngredientInfo(
            @UserResolver User user,
            @RequestBody @Valid IngredientGetRequest request
    ) throws URISyntaxException {
        return ApplicationResponse.onSuccess(ingredientFindUseCase.getIngredient(user, request));
    }
}
