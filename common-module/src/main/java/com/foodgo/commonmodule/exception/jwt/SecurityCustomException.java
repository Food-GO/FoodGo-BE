package com.foodgo.commonmodule.exception.jwt;

import com.foodgo.commonmodule.exception.common.BaseErrorCode;
import com.foodgo.commonmodule.exception.common.CustomException;

import lombok.Getter;

@Getter
public class SecurityCustomException extends CustomException {

	private final Throwable cause;

	public SecurityCustomException(BaseErrorCode errorCode) {
		super(errorCode);
		this.cause = null;
	}

	public SecurityCustomException(BaseErrorCode errorCode, Throwable cause) {
		super(errorCode);
		this.cause = cause;
	}
}
