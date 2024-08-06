package com.foodgo.coremodule.user.dto.response;

import com.foodgo.coremodule.user.domain.User;
import com.foodgo.coremodule.user.enums.DiseaseType;
import com.foodgo.coremodule.user.enums.UsageType;

import lombok.Builder;

@Builder
public record UserRegisterResponse(
	Long id,
	String username,
	String nickname,
	String imageUrl,
	UsageType usageType,
	DiseaseType diseaseType,
	String lifeStyle,
	String allergy
) {

	public static UserRegisterResponse from(User user) {
		return UserRegisterResponse.builder()
			.id(user.getId())
			.username(user.getUsername())
			.nickname(user.getNickname())
			.imageUrl(user.getImageUrl())
			.usageType(user.getUsageType())
			.diseaseType(user.getDiseaseType())
			.lifeStyle(user.getLifeStyle())
			.allergy(user.getAllergy())
			.build();
	}
}
