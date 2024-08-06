package com.foodgo.coremodule.user.dto.response;

import com.foodgo.coremodule.user.domain.User;
import com.foodgo.coremodule.user.enums.DiseaseType;
import com.foodgo.coremodule.user.enums.UsageType;

import lombok.Builder;

@Builder
public record UserDetailGetResponse(
	Long id,
	String username,
	String imageUrl,
	UsageType usageType,
	DiseaseType diseaseType,
	String lifeStyle
) {

	public static UserDetailGetResponse from(User user) {
		return UserDetailGetResponse.builder()
			.id(user.getId())
			.username(user.getUsername())
			.imageUrl(user.getImageUrl())
			.usageType(user.getUsageType())
			.diseaseType(user.getDiseaseType())
			.lifeStyle(user.getLifeStyle())
			.build();
	}
}
