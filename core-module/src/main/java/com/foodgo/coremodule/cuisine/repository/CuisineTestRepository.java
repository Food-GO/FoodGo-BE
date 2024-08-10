package com.foodgo.coremodule.cuisine.repository;

import com.foodgo.coremodule.cuisine.domain.CuisineTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineTestRepository extends JpaRepository<CuisineTest, Long> {

    CuisineTest findCuisineTestByUserId(Long userId);
}
