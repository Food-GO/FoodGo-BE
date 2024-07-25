package com.foodgo.apimodule.cuisine.dto;

public record IngredientInfo(
        Long ingredientId,
        String name,
        String quantity,
        String imageUrl
) {
}
