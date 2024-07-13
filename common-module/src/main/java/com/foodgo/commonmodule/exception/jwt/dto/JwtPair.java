package com.foodgo.commonmodule.exception.jwt.dto;

public record JwtPair(
	String accessToken,
	String refreshToken
) {
}
