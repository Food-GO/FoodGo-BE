package com.foodgo.coremodule.ingredient.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.web.reactive.function.client.WebClient;

import com.foodgo.coremodule.ingredient.dto.request.IngredientGetRequest;
import com.foodgo.coremodule.ingredient.dto.response.IngredientGetResponse;
import com.foodgo.coremodule.ingredient.repository.IngredientInfoRepository;
import com.foodgo.coremodule.user.domain.User;

@RequiredArgsConstructor
@Transactional
@Service
public class IngredientService {

	private final IngredientInfoRepository ingredientInfoRepository;

	public IngredientGetResponse.Row getIngredient(User user, IngredientGetRequest request) throws URISyntaxException {
		String apiKey = "abe72ba4fe0445af8c77";
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
