package com.foodgo.apimodule.cuisine.presentation;

import com.foodgo.apimodule.cuisine.application.QuisineFindUseCase;
import com.foodgo.apimodule.cuisine.application.QuisineSaveUseCase;
import com.foodgo.apimodule.cuisine.dto.IngredientAddReq;
import com.foodgo.apimodule.cuisine.dto.IngredientInfo;
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

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/quisine")
@Validated
@Tag(name = "Quisine", description = "음식 추천 관련 API")
public class QuisineController {

    private final QuisineFindUseCase quisineFindUseCase;
    private final QuisineSaveUseCase quisineSaveUseCase;

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

        final List<IngredientInfo> ingredientInfo = quisineFindUseCase.findIngredientInfo(user);
        return ApplicationResponse.ok(ingredientInfo);
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

        quisineSaveUseCase.saveIngredient(addReq, multipartFile, user);
        return ApplicationResponse.ok("식재료 리스트 추가되었습니다.");
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

        quisineSaveUseCase.deleteIngredient(ingredientId);
        return ApplicationResponse.ok("식재료 리스트가 삭제되었습니다.");
    }

}
