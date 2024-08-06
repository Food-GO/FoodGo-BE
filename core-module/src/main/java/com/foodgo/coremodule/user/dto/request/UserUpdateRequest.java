package com.foodgo.coremodule.user.dto.request;

import com.foodgo.coremodule.user.enums.DiseaseType;
import com.foodgo.coremodule.user.enums.UsageType;

public record UserUpdateRequest(
	String nickname,
	UsageType usageType,
	DiseaseType diseaseType,
	String lifeStyle,
	String allergy
) {
}
