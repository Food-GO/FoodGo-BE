package com.foodgo.coremodule.ingredient.entity;

import com.foodgo.commonmodule.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "ingredientsInfo")
public class IngredientInfo extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ingredient_id")
	private Long id;

	@Column(name = "ingredient_name", nullable = false)
	private String ingredientName;

	@Column(name = "ingredient_Group", nullable = false)
	private String ingredientGroup;

	@Column(name = "nutr_cont1")
	private double nutrCont1; // 열량(kcal)(1회제공량당)

	@Column(name = "nutr_cont2")
	private double nutrCont2; // 탄수화물(g)(1회제공량당)

	@Column(name = "nutr_cont3")
	private double nutrCont3; // 단백질(g)(1회제공량당)

	@Column(name = "nutr_cont4")
	private double nutrCont4; // 지방(g)(1회제공량당)
}
