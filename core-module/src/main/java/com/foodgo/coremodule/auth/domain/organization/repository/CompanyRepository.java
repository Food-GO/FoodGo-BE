package com.foodgo.coremodule.auth.domain.organization.repository;

import com.foodgo.coremodule.auth.domain.organization.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
