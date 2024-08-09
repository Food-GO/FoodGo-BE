package com.foodgo.coremodule.ingredient.service;

import com.foodgo.coremodule.ingredient.repository.IngredientInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientQueryService {

    private final IngredientInfoRepository ingredientInfoRepository;
}
