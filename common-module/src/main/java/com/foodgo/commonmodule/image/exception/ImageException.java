package com.foodgo.commonmodule.image.exception;

import com.foodgo.commonmodule.common.BaseErrorCode;
import lombok.Getter;

@Getter
public class ImageException extends RuntimeException {

    private final BaseErrorCode errorCode;

    private final Throwable cause;

    public ImageException(BaseErrorCode errorCode) {
        this.errorCode = errorCode;
        this.cause = null;
    }

    public ImageException(BaseErrorCode errorCode, Throwable cause) {
        this.errorCode = errorCode;
        this.cause = cause;
    }
}