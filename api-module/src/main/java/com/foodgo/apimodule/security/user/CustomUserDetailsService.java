package com.foodgo.apimodule.security.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foodgo.apimodule.user.exception.UserErrorCode;
import com.foodgo.apimodule.user.exception.UserExceptionHandler;
import com.foodgo.coremodule.user.domain.User;
import com.foodgo.coremodule.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new UserExceptionHandler(UserErrorCode.USER_NOT_FOUND));

		log.info("[*] User found : " + user.getUsername());

		return new CustomUserDetails(user.getUsername(), user.getPassword(), user.isStaff());
	}
}
