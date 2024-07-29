package com.foodgo.coremodule.quisine.service;

import com.foodgo.coremodule.quisine.domain.Ingredient;
import com.foodgo.coremodule.quisine.exception.QuisineErrorCode;
import com.foodgo.coremodule.quisine.exception.QuisineException;
import com.foodgo.coremodule.quisine.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuisineQueryService {

    private final IngredientRepository ingredientRepository;

    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new QuisineException(QuisineErrorCode.NO_INGREDIENT_INFO));
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
}
