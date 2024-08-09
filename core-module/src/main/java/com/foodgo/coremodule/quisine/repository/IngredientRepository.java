package com.foodgo.coremodule.quisine.repository;

import com.foodgo.coremodule.quisine.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findIngredientsByUserId(Long userId);
}
