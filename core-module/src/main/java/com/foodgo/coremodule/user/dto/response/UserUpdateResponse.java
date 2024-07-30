package com.foodgo.coremodule.user.dto.response;

import com.foodgo.coremodule.user.domain.User;

import lombok.Builder;

@Builder
public record UserUpdateResponse(
	String username,
	String imageUrl
) {
	public static UserUpdateResponse from(User user) {
		return UserUpdateResponse.builder()
			.username(user.getUsername())
			.imageUrl(user.getImageUrl())
			.build();
	}
}
