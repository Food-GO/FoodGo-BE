package com.foodgo.coremodule.quisine.exception;

import com.foodgo.commonmodule.common.BaseErrorCode;
import lombok.Getter;

@Getter
public class QuisineException extends RuntimeException {

    private final BaseErrorCode errorCode;

    private final Throwable cause;

    public QuisineException(BaseErrorCode errorCode) {
        this.errorCode = errorCode;
        this.cause = null;
    }

    public QuisineException(BaseErrorCode errorCode, Throwable cause) {
        this.errorCode = errorCode;
        this.cause = cause;
    }
}