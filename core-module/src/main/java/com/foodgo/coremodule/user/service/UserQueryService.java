package com.foodgo.coremodule.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodgo.commonmodule.user.exception.UserErrorCode;
import com.foodgo.commonmodule.user.exception.UserExceptionHandler;
import com.foodgo.coremodule.user.domain.User;
import com.foodgo.coremodule.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserQueryService {

	private final UserRepository userRepository;

	public User findByUserName(String username) {
		return userRepository.findByUsername(username)
			.orElseThrow(() -> new UserExceptionHandler(UserErrorCode.USER_NOT_FOUND));
	}
}
