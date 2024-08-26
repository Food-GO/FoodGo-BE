package com.foodgo.apimodule.ingredient.dto;

public record IngredientAddReq(
        String name,
        String quantity,
        String imageUrl
) {
}
