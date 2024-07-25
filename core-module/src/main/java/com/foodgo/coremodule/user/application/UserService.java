package com.foodgo.coremodule.user.application;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodgo.commonmodule.jwt.dto.JwtDto;
import com.foodgo.coremodule.user.domain.User;
import com.foodgo.coremodule.user.dto.request.UserRegisterRequest;
import com.foodgo.coremodule.user.dto.response.UserRegisterResponse;
import com.foodgo.coremodule.user.repository.UserRepository;
import com.foodgo.coremodule.security.util.JwtUtil;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public UserRegisterResponse register(UserRegisterRequest request) {

		String encodedPw = passwordEncoder.encode(request.password());
		User newUser = request.toEntity(encodedPw);

		return UserRegisterResponse.from(userRepository.save(newUser));
	}

	public JwtDto reissueToken(String refreshToken) {
		return jwtUtil.reissueToken(refreshToken);
	}
}