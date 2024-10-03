package com.foodgo.commonmodule.common;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    HttpStatus getHttpStatus();

    String getCode();

    String getMessage();

    ApplicationResponse<Void> getErrorResponse();
}
