package com.foodgo.apimodule.cuisine.application;

import com.foodgo.apimodule.ingredient.dto.IngredientAddReq;
import com.foodgo.apimodule.cuisine.dto.TestResultType;
import com.foodgo.apimodule.ingredient.mapper.IngredientMapper;
import com.foodgo.commonmodule.image.service.AwsS3Service;
import com.foodgo.coremodule.cuisine.domain.CuisineTest;
import com.foodgo.coremodule.cuisine.domain.Ingredient;
import com.foodgo.coremodule.cuisine.service.CuisineQueryService;
import com.foodgo.coremodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class CuisineSaveUseCase {

    private final CuisineQueryService cuisineQueryService;
    private final AwsS3Service awsS3Service;

    public void saveIngredient(IngredientAddReq addReq, MultipartFile multipartFile, User user) {

        String imageUrl = awsS3Service.uploadFile(multipartFile);
        Ingredient ingredient = IngredientMapper.toIngredientEntity(addReq, user, imageUrl);

        cuisineQueryService.saveIngredient(ingredient);
    }


    public void deleteIngredient(Long ingredientId) {

        cuisineQueryService.deleteIngredient(ingredientId);
    }

    public void saveCuisineTest(User user, TestResultType resultType) {

        final CuisineTest cuisineTest = CuisineTest.builder().type(resultType.testType()).user(user).build();
        cuisineQueryService.saveCuisineTest(cuisineTest);
    }
}
