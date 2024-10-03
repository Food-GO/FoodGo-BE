package com.foodgo.coremodule.cuisine.service;

import com.foodgo.coremodule.cuisine.domain.CuisineTest;
import com.foodgo.coremodule.cuisine.domain.Ingredient;
import com.foodgo.coremodule.cuisine.domain.TestType;
import com.foodgo.coremodule.cuisine.exception.CuisineErrorCode;
import com.foodgo.coremodule.cuisine.exception.CuisineException;
import com.foodgo.coremodule.cuisine.repository.CuisineTestRepository;
import com.foodgo.coremodule.cuisine.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuisineQueryService {

    private final IngredientRepository ingredientRepository;
    private final CuisineTestRepository cuisineTestRepository;

    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new CuisineException(CuisineErrorCode.NO_INGREDIENT_INFO));
    }

    public List<Ingredient> findIngredientsByUserId(Long userId) {
        return ingredientRepository.findIngredientsByUserId(userId);
    }

    public void saveIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long id) {
        Ingredient ingredient = findById(id);
        ingredientRepository.delete(ingredient);
    }

    public void saveCuisineTest(CuisineTest cuisineTest) {
        cuisineTestRepository.save(cuisineTest);
    }

    public TestType findTestType(Long userId) {
        return cuisineTestRepository.findCuisineTestByUserId(userId).getType();
    }
}
