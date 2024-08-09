package com.foodgo.apimodule.ingredient.mapper;

import com.foodgo.apimodule.ingredient.dto.IngredientAddReq;
import com.foodgo.apimodule.ingredient.dto.IngredientInfo;
import com.foodgo.coremodule.cuisine.domain.Ingredient;
import com.foodgo.coremodule.user.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IngredientMapper {

    public static IngredientInfo toInfoDTO(Ingredient ingredient) {
        return new IngredientInfo(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getQuantity(),
                ingredient.getImageUrl()
        );
    }

    public static Ingredient toIngredientEntity(IngredientAddReq addReq, User user, String imageUrl) {
        return Ingredient.builder()
                .name(addReq.name())
                .quantity(addReq.quantity())
                .imageUrl(imageUrl)
                .user(user)
                .build();
    }
}
