package com.foodgo.coremodule.auth.domain.organization.repository;

import com.foodgo.coremodule.auth.domain.organization.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
