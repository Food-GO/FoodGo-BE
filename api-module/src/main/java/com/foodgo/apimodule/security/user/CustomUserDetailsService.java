package com.foodgo.apimodule.security.user;

import com.foodgo.commonmodule.exception.jwt.SecurityCustomException;
import com.foodgo.commonmodule.exception.jwt.SecurityErrorCode;
import com.foodgo.coremodule.auth.domain.organization.Organization;
import com.foodgo.coremodule.auth.domain.organization.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final OrganizationRepository organizationRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Organization findOrganization = organizationRepository.findOrganizationByEmail(email)
                .orElseThrow(() -> new SecurityCustomException(SecurityErrorCode.TOKEN_ORGANIZATION_NOT_FOND));

        log.info("[*] Organization found : " + findOrganization.getEmail());

        return new CustomUserDetails(findOrganization);
    }
}
