package com.foodgo.apimodule.cuisine.application;

import com.foodgo.apimodule.cuisine.dto.IngredientInfo;
import com.foodgo.apimodule.cuisine.mapper.IngredientMapper;
import com.foodgo.coremodule.quisine.domain.Ingredient;
import com.foodgo.coremodule.quisine.service.QuisineQueryService;
import com.foodgo.coremodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuisineFindUseCase {

    private final QuisineQueryService quisineQueryService;

    public List<IngredientInfo> findIngredientInfo(User user) {

        List<IngredientInfo> infoList = new ArrayList<>();

        List<Ingredient> ingredients = quisineQueryService.findIngredientsByUserId(user.getId());
        for (Ingredient ingredient : ingredients) {
            final IngredientInfo infoDTO = IngredientMapper.toInfoDTO(ingredient);
            infoList.add(infoDTO);
        }

        return infoList;
    }
}
