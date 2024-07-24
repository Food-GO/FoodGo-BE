package com.foodgo.commonmodule.jwt.dto;

public record JwtDto(
	String accessToken,
	String refreshToken
) {
}
