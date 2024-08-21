package com.foodgo.apimodule.cuisine.presentation;

import com.foodgo.apimodule.cuisine.application.CuisineFindUseCase;
import com.foodgo.apimodule.cuisine.application.CuisineSaveUseCase;
import com.foodgo.apimodule.cuisine.dto.RecipeDTO;
import com.foodgo.apimodule.cuisine.dto.TestResultType;
import com.foodgo.apimodule.ingredient.dto.IngredientAddReq;
import com.foodgo.apimodule.ingredient.dto.IngredientInfo;
import com.foodgo.commonmodule.common.ApplicationResponse;
import com.foodgo.coremodule.security.annotation.UserResolver;
import com.foodgo.coremodule.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cuisine")
@Validated
@Tag(name = "cuisine", description = "음식 추천 관련 API")
public class CuisineController {

    private final CuisineFindUseCase cuisineFindUseCase;
    private final CuisineSaveUseCase cuisineSaveUseCase;

    // 전체 조회
    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "식재료 리스트 확인 성공",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "식재료 리스트 확인 API", description = "식재료 리스트 확인 API 입니다.")
    public ApplicationResponse<List<IngredientInfo>> findIngredientList(@UserResolver User user) {

        final List<IngredientInfo> ingredientInfo = cuisineFindUseCase.findIngredientInfo(user);
        return ApplicationResponse.onSuccess(ingredientInfo);
    }

    // 식재료 추가하기
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "식재료 리스트 추가 성공",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "식재료 리스트 추가 API", description = "식재료 리스트 추가 API 입니다.")
    public ApplicationResponse<String> addIngredientList(@UserResolver User user,
                                                         @RequestPart(value = "dto") IngredientAddReq addReq,
                                                         @RequestPart(value = "file") MultipartFile multipartFile) {

        cuisineSaveUseCase.saveIngredient(addReq, multipartFile, user);
        return ApplicationResponse.onSuccess("식재료 리스트 추가되었습니다.");
    }

    // 식재료 삭제하기
    @DeleteMapping("{ingredientId}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "식재료 리스트 삭제 성공",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "식재료 리스트 삭제 API", description = "식재료 리스트 삭제 API 입니다.")
    public ApplicationResponse<String> deleteIngredient(@PathVariable Long ingredientId) {

        cuisineSaveUseCase.deleteIngredient(ingredientId);
        return ApplicationResponse.onSuccess("식재료 리스트가 삭제되었습니다.");
    }

    // 테스트 조회
    @GetMapping("/test")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "테스트 결과 조회",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "테스트 결과 조회 API", description = "테스트 결과 조회 API 입니다.")
    public ApplicationResponse<TestResultType> findTestResult(
            @UserResolver User user
    ) {

        TestResultType type = cuisineFindUseCase.findTestResult(user);
        return ApplicationResponse.onSuccess(type);
    }

    // 테스트 결과
    @PostMapping("/test")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "테스트 결과 저장",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "테스트 결과 저장 API", description = "테스트 결과 저장 API 입니다.")
    public ApplicationResponse<String> saveTestResult(
            @UserResolver User user,
            @RequestBody TestResultType resultType) {

        cuisineSaveUseCase.saveCuisineTest(user, resultType);
        return ApplicationResponse.onSuccess("결과가 저장되었습니다.");
    }

    // 조리법 결과 가져오기
    @PostMapping("/recipe")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "조리법 결과 가져오기",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "조리법 결과 가져오기 API", description = "조리법 결과 가져오기 API 입니다.")
    public ApplicationResponse<List<RecipeDTO.RecipeDetail>> findRecipeResult(
            @RequestBody RecipeDTO.Request request) throws URISyntaxException {

        final List<RecipeDTO.RecipeDetail> recipeResult = cuisineFindUseCase.findRecipeResult(request);
        return ApplicationResponse.onSuccess(recipeResult);
    }

}
