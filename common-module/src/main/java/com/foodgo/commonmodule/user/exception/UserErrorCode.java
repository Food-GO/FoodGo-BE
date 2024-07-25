package com.foodgo.commonmodule.user.exception;

import org.springframework.http.HttpStatus;

import com.foodgo.commonmodule.common.ApiResponse;
import com.foodgo.commonmodule.common.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USR4000", "존재하지 않는 사용자입니다."),
	PASSWORD_NOT_EQUAL(HttpStatus.BAD_REQUEST, "USR4001", "비밀번호가 일치하지 않습니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

	@Override
	public ApiResponse<Void> getErrorResponse() {
		return ApiResponse.onFailure(code, message);
	}
}