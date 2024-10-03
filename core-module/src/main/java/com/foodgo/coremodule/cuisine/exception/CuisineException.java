package com.foodgo.coremodule.cuisine.exception;

import com.foodgo.commonmodule.common.BaseErrorCode;
import lombok.Getter;

@Getter
public class CuisineException extends RuntimeException {

    private final BaseErrorCode errorCode;

    private final Throwable cause;

    public CuisineException(BaseErrorCode errorCode) {
        this.errorCode = errorCode;
        this.cause = null;
    }

    public CuisineException(BaseErrorCode errorCode, Throwable cause) {
        this.errorCode = errorCode;
        this.cause = cause;
    }
}