package com.foodgo.apimodule.user.exception;

import com.foodgo.commonmodule.exception.common.BaseErrorCode;
import com.foodgo.commonmodule.exception.common.CustomException;

public class UserExceptionHandler extends CustomException {
	public UserExceptionHandler(BaseErrorCode code) {
		super(code);
	}
}
