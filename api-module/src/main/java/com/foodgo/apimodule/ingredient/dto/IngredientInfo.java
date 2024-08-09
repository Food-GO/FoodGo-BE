package com.foodgo.apimodule.ingredient.dto;

public record IngredientInfo(
        Long ingredientId,
        String name,
        String quantity,
        String imageUrl
) {
}
