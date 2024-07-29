package com.foodgo.apimodule.cuisine.application;

import com.foodgo.apimodule.cuisine.dto.IngredientAddReq;
import com.foodgo.apimodule.cuisine.mapper.IngredientMapper;
import com.foodgo.commonmodule.image.service.AwsS3Service;
import com.foodgo.coremodule.quisine.domain.Ingredient;
import com.foodgo.coremodule.quisine.service.QuisineQueryService;
import com.foodgo.coremodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class QuisineSaveUseCase {

    private final QuisineQueryService quisineQueryService;
    private final AwsS3Service awsS3Service;

    public void saveIngredient(IngredientAddReq addReq, MultipartFile multipartFile, User user) {

        String imageUrl = awsS3Service.uploadFile(multipartFile);
        Ingredient ingredient = IngredientMapper.toIngredientEntity(addReq, user, imageUrl);

        quisineQueryService.saveIngredient(ingredient);
    }

    public void deleteIngredient(Long ingredientId) {

        quisineQueryService.deleteIngredient(ingredientId);
    }
}
