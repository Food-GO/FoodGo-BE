package com.foodgo.commonmodule.image.exception;

import com.foodgo.commonmodule.common.BaseErrorCode;

public class FileDeleteException extends ImageException {
    public FileDeleteException(BaseErrorCode errorCode) {
        super(errorCode);
    }

    public FileDeleteException(BaseErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
