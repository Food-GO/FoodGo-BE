package com.foodgo.commonmodule.image.exception;

import com.foodgo.commonmodule.common.BaseErrorCode;

public class FileExtensionException extends ImageException {
    public FileExtensionException(BaseErrorCode errorCode) {
        super(errorCode);
    }

    public FileExtensionException(BaseErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
