package com.foodgo.coremodule.user.dto.response;

import com.foodgo.coremodule.user.domain.User;

import lombok.Builder;

@Builder
public record UserRegisterResponse(
	Long id,
	String username
) {

	public static UserRegisterResponse from(User user) {
		return UserRegisterResponse.builder()
			.id(user.getId())
			.username(user.getUsername())
			.build();
	}
}
