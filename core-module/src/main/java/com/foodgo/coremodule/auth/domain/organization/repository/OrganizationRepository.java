package com.foodgo.coremodule.auth.domain.organization.repository;

import com.foodgo.coremodule.auth.domain.organization.Organization;
import com.foodgo.coremodule.auth.domain.organization.enums.OrganizationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

	Optional<Organization> findOrganizationByEmail(String email);

	@Query("SELECT o FROM Organization o WHERE o.organizationType = :organizationType")
	Page<Organization> findOrganizations(OrganizationType organizationType, Pageable pageable);

	@Query("SELECT COUNT(o) FROM Organization o WHERE o.organizationType = :organizationType")
	Long countByOrganizationType(OrganizationType organizationType);

	Boolean existsByName(String name);

	List<Organization> findByNameContains(String name, Pageable pageable);

	@Query("SELECT COUNT(o) FROM Organization o WHERE o.name LIKE '%:keyword%'")
	Long countByNameContains(String organizationType);

	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM Organization o WHERE o.email = :email")
	Boolean checkDuplicateEmail(String email);
}
