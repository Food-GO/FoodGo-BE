package com.foodgo.coremodule.ingredient.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodgo.coremodule.ingredient.entity.IngredientInfo;

public interface IngredientInfoRepository extends JpaRepository<IngredientInfo, Long> {

	Optional<IngredientInfo> findByIngredientName(String ingredientName);
}
