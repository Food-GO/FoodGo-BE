package com.foodgo.coremodule.auth.domain.organization.repository;

import com.foodgo.coremodule.auth.domain.organization.OrganizationLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationLinkRepository extends JpaRepository<OrganizationLink, Long> {
}
