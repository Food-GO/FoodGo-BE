package com.foodgo.coremodule.community.exception;

import com.foodgo.commonmodule.common.BaseErrorCode;
import lombok.Getter;

@Getter
public class ChallengeException extends RuntimeException {

    private final BaseErrorCode errorCode;

    private final Throwable cause;

    public ChallengeException(BaseErrorCode errorCode) {
        this.errorCode = errorCode;
        this.cause = null;
    }

    public ChallengeException(BaseErrorCode errorCode, Throwable cause) {
        this.errorCode = errorCode;
        this.cause = cause;
    }
}