package com.foodgo.commonmodule.user.exception;

import com.foodgo.commonmodule.common.BaseErrorCode;
import com.foodgo.commonmodule.common.CustomException;

public class UserExceptionHandler extends CustomException {
	public UserExceptionHandler(BaseErrorCode code) {
		super(code);
	}
}
