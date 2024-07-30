package com.foodgo.coremodule.security.jwt.dto;

public record JwtDto(
	String accessToken,
	String refreshToken
) {
}
