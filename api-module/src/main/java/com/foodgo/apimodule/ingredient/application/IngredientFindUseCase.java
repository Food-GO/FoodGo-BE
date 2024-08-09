package com.foodgo.apimodule.ingredient.application;

import com.foodgo.coremodule.ingredient.dto.request.IngredientGetRequest;
import com.foodgo.coremodule.ingredient.dto.response.IngredientGetResponse;
import com.foodgo.coremodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientFindUseCase {

    @Value("${spring.openapi.key.recipe}")
    private String apiKey;

    public IngredientGetResponse.Row getIngredient(User user, IngredientGetRequest request) throws URISyntaxException {

        String apiUrl = "http://openapi.foodsafetykorea.go.kr/api/" + apiKey + "/I2790/json/1/100/DESC_KOR=" + request.descKor();
        URI uri = new URI(apiUrl);

        WebClient webClient = WebClient.create();
        IngredientGetResponse response = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(IngredientGetResponse.class)
                .doOnError(error -> {
                    throw new RuntimeException("API 호출 오류: " + error.getMessage());
                })
                .block();

        assert response != null;
        List<IngredientGetResponse.Row> filteredRows = response.getI2790().getRow().stream()
                .filter(row -> groupNameMatches(row, request.groupName()))
                .toList();

        return filteredRows.isEmpty() ? null : filteredRows.get(0);
    }

    private boolean groupNameMatches(IngredientGetResponse.Row row, String groupName) {
        return groupName == null || groupName.isEmpty() || groupName.equals(row.getGroupName());
    }
}
