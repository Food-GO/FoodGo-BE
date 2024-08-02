package com.foodgo.coremodule.user.dto.response;

import com.foodgo.coremodule.user.domain.User;

import lombok.Builder;

@Builder
public record UserDetailGetResponse(
	Long id,
	String username,
	String imageUrl
) {

	public static UserDetailGetResponse from(User user) {
		return UserDetailGetResponse.builder()
			.id(user.getId())
			.username(user.getUsername())
			.imageUrl(user.getImageUrl())
			.build();
	}
}
