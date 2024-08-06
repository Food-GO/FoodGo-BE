package com.foodgo.coremodule.ingredient.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodgo.coremodule.ingredient.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

	Optional<Ingredient> findByIngredientName(String ingredientName);
}
