package com.foodgo.coremodule.auth.domain.organization;

import com.foodgo.coremodule.auth.domain.organization.enums.ClubType;
import com.foodgo.coremodule.auth.domain.organization.enums.OrganizationType;
import com.foodgo.coremodule.auth.domain.organization.enums.ProfileStatus;
import com.foodgo.coremodule.auth.domain.organization.enums.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "club")
@DiscriminatorValue("CLUB")
public class Club extends Organization {
	@Column(name = "member_count")
	private int memberCount;

	@Enumerated(EnumType.STRING)
	@Column(name = "company_type")
	private ClubType clubType;
	
	// 초기 생성 시 사용
	public Club(String name, String email, String password) {
		super(name, email, password, null, null, OrganizationType.CLUB, ProfileStatus.INACTIVE, Role.GUEST);
		this.memberCount = 0;
		this.clubType = ClubType.NONE;
	}

	// 프로필 업데이트 시 사용
	public void updateInfo(
		String name,
		String description,
		String imageUrl,
		int memberCount,
		ClubType clubType,
		ProfileStatus profileStatus) {
		super.updateInfo(name, description, imageUrl, profileStatus);
		this.memberCount = memberCount;
		this.clubType = clubType;
	}

	@Override
	public String getSubType() {
		return clubType.name();
	}
}
