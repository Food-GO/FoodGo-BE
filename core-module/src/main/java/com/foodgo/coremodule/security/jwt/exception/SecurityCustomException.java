package com.foodgo.coremodule.security.jwt.exception;

import com.foodgo.commonmodule.common.BaseErrorCode;
import com.foodgo.commonmodule.common.CustomException;

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
