package com.foodgo.commonmodule.image.exception;

import com.foodgo.commonmodule.common.BaseErrorCode;

public class FileUploadException extends ImageException {
    public FileUploadException(BaseErrorCode errorCode) {
        super(errorCode);
    }

    public FileUploadException(BaseErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
