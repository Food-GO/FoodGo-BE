package com.foodgo.coremodule.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.foodgo.commonmodule.image.service.AwsS3Service;
import com.foodgo.commonmodule.jwt.dto.JwtDto;
import com.foodgo.coremodule.user.domain.User;
import com.foodgo.coremodule.user.dto.request.PasswordUpdateRequest;
import com.foodgo.coremodule.user.dto.request.UserRegisterRequest;
import com.foodgo.coremodule.user.dto.request.UserUpdateRequest;
import com.foodgo.coremodule.user.dto.response.UserRegisterResponse;
import com.foodgo.coremodule.user.dto.response.UserUpdateResponse;
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
	private final AwsS3Service awsS3Service;

	public UserRegisterResponse register(UserRegisterRequest request, MultipartFile file) {
		String imageUrl = null;
		if (file != null && !file.isEmpty()) {
			imageUrl = awsS3Service.uploadFile(file);
		}
		final User user = request.toEntity(passwordEncoder.encode(request.password()));
		user.setImageUrl(imageUrl);
		userRepository.save(user);
		return UserRegisterResponse.from(user);
	}
	public JwtDto reissueToken(String refreshToken) {
		return jwtUtil.reissueToken(refreshToken);
	}

	public void updatePassword(User user, PasswordUpdateRequest request) {
		String encodedNewPassword = passwordEncoder.encode(request.password());
		user.updatePassword(encodedNewPassword);
	}

	public UserUpdateResponse updateMyUser(User user, MultipartFile file) {
		String imageUrl = null;
		if (file != null && !file.isEmpty()) {
			imageUrl = awsS3Service.uploadFile(file);
		}
		user.update(imageUrl);
		return UserUpdateResponse.from(user);
	}

	public void deactivate(User user) {
		user.deactivate();
	}
}
