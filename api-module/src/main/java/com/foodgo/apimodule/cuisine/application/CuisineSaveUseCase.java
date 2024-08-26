package com.foodgo.apimodule.cuisine.application;

import com.foodgo.apimodule.cuisine.dto.TestResultType;
import com.foodgo.apimodule.ingredient.dto.IngredientAddReq;
import com.foodgo.apimodule.ingredient.mapper.IngredientMapper;
import com.foodgo.coremodule.cuisine.domain.CuisineTest;
import com.foodgo.coremodule.cuisine.domain.Ingredient;
import com.foodgo.coremodule.cuisine.service.CuisineQueryService;
import com.foodgo.coremodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CuisineSaveUseCase {

    private final CuisineQueryService cuisineQueryService;

    public void saveIngredient(IngredientAddReq addReq, User user) {

        Ingredient ingredient = IngredientMapper.toIngredientEntity(addReq, user);
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
