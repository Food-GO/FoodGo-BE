package com.foodgo.apimodule.cuisine.mapper;

import com.foodgo.apimodule.cuisine.dto.IngredientAddReq;
import com.foodgo.apimodule.cuisine.dto.IngredientInfo;
import com.foodgo.coremodule.quisine.domain.Ingredient;
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
