package com.foodgo.commonmodule.exception.jwt;

import com.foodgo.commonmodule.exception.common.BaseErrorCode;
import lombok.Getter;

@Getter
public class SecurityCustomException extends RuntimeException {

	private final BaseErrorCode errorCode;

	private final Throwable cause;

	public SecurityCustomException(BaseErrorCode errorCode) {
		this.errorCode = errorCode;
		this.cause = null;
	}

	public SecurityCustomException(BaseErrorCode errorCode, Throwable cause) {
		this.errorCode = errorCode;
		this.cause = cause;
	}
}
