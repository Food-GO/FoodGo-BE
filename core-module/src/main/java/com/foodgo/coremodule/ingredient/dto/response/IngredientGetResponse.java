package com.foodgo.coremodule.ingredient.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class IngredientGetResponse {

	@JsonProperty("I2790")
	private I2790 i2790;

	@Getter
	@Setter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class I2790 {
		@JsonProperty("total_count")
		private String totalCount;

		@JsonProperty("row")
		private List<Row> row;

		@JsonProperty("RESULT")
		private Result result;
	}

	@Getter
	@Setter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Row {

		@JsonProperty("DESC_KOR")
		private String descKor;          // 식품이름

		@JsonProperty("GROUP_NAME")
		private String groupName;        // 식품군

		@JsonProperty("NUTR_CONT1")
		private String nutrCont1;        // 열량(kcal)

		@JsonProperty("NUTR_CONT2")
		private String nutrCont2;        // 탄수화물

		@JsonProperty("NUTR_CONT3")
		private String nutrCont3;        // 단백질

		@JsonProperty("NUTR_CONT4")
		private String nutrCont4;        // 지방

		@JsonProperty("NUTR_CONT5")
		private String nutrCont5;        // 당류

		@JsonProperty("NUTR_CONT6")
		private String nutrCont6;        // 나트륨

		@JsonProperty("NUTR_CONT7")
		private String nutrCont7;        // 콜레스테롤

		@JsonProperty("NUTR_CONT8")
		private String nutrCont8;        // 포화지방산

		@JsonProperty("NUTR_CONT9")
		private String nutrCont9;        // 트랜스지방
	}

	@Getter
	@Setter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Result {
		@JsonProperty("MSG")
		private String msg;

		@JsonProperty("CODE")
		private String code;
	}
}
