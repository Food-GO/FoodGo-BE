package com.foodgo.coremodule.user.dto.request;

import com.foodgo.coremodule.user.domain.User;
import com.foodgo.coremodule.user.enums.DiseaseType;
import com.foodgo.coremodule.user.enums.RoleType;
import com.foodgo.coremodule.user.enums.UsageType;
import com.foodgo.coremodule.user.enums.UserStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegisterRequest(

	@NotNull(message = "[ERROR] 아이디 입력은 필수입니다.")
	String username,

	@NotNull(message = "[ERROR] 비밀번호 입력은 필수 입니다.")
	@Size(min = 8, message = "[ERROR] 비밀번호는 최소 8자리 이이어야 합니다.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,64}$", message = "[ERROR] 비밀번호는 8자 이상, 64자 이하이며 특수문자 한 개를 포함해야 합니다.")
	String password,

	@NotNull(message = "[ERROR] 닉네임 입력은 필수입니다.")
	String nickname,

	@NotNull
	UsageType usageType,

	@NotNull
	DiseaseType diseaseType,

	@NotNull
	String lifeStyle,

	@NotNull
	String allergy

) {

	public User toEntity(String encodedPw) {
		return User.builder()
			.username(username)
			.password(encodedPw)
			.nickname(nickname)
			.usageType(usageType)
			.diseaseType(diseaseType)
			.lifeStyle(lifeStyle)
			.allergy(allergy)
			.roleType(RoleType.USER)
			.userStatus(UserStatus.ACTIVE)
			.build();
	}
}
