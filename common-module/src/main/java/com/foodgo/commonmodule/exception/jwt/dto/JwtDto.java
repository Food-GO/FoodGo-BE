package com.foodgo.commonmodule.exception.jwt.dto;

public record JwtDto(
	String accessToken,
	String refreshToken
) {
}
