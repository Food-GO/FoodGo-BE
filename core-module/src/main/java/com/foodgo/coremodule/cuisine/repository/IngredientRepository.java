package com.foodgo.coremodule.cuisine.repository;

import com.foodgo.coremodule.cuisine.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findIngredientsByUserId(Long userId);
}
