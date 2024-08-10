package com.foodgo.apimodule.cuisine.application;

import com.foodgo.apimodule.cuisine.dto.RecipeDTO;
import com.foodgo.apimodule.cuisine.dto.TestResultType;
import com.foodgo.apimodule.ingredient.dto.IngredientInfo;
import com.foodgo.apimodule.ingredient.mapper.IngredientMapper;
import com.foodgo.coremodule.cuisine.domain.Ingredient;
import com.foodgo.coremodule.cuisine.domain.TestType;
import com.foodgo.coremodule.cuisine.exception.CuisineErrorCode;
import com.foodgo.coremodule.cuisine.exception.CuisineException;
import com.foodgo.coremodule.cuisine.service.CuisineQueryService;
import com.foodgo.coremodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CuisineFindUseCase {

    private final CuisineQueryService cuisineQueryService;

    @Value("${spring.openapi.key.recipe}")
    private String apiKey;

    public List<IngredientInfo> findIngredientInfo(User user) {

        List<IngredientInfo> infoList = new ArrayList<>();

        List<Ingredient> ingredients = cuisineQueryService.findIngredientsByUserId(user.getId());
        for (Ingredient ingredient : ingredients) {
            final IngredientInfo infoDTO = IngredientMapper.toInfoDTO(ingredient);
            infoList.add(infoDTO);
        }

        return infoList;
    }

    public TestResultType findTestResult(User user) {

        TestType test = cuisineQueryService.findTestType(user.getId());
        return new TestResultType(test);
    }

    public List<RecipeDTO.RecipeDetail> findRecipeResult(RecipeDTO.Request request) throws URISyntaxException {

        String apiUrl = "http://openapi.foodsafetykorea.go.kr/api/" +
                apiKey + "/COOKRCP01/json/1/100/RCP_NM=" + request.rcpNm();

        URI uri = new URI(apiUrl);

        WebClient webClient = WebClient.create();
        RecipeDTO.TotalResponse response = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(RecipeDTO.TotalResponse.class)
                .doOnError(error -> {
                    throw new CuisineException(CuisineErrorCode.OPEN_API_INFO_ERROR);
                })
                .block();

        // row 리스트만 추출
        return response.COOKRCP01().row();
    }
}
