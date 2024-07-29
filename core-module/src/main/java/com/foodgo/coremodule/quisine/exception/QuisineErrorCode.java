package com.foodgo.coremodule.quisine.exception;

import com.foodgo.commonmodule.common.ApiResponse;
import com.foodgo.commonmodule.common.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum QuisineErrorCode implements BaseErrorCode {

    NO_INGREDIENT_INFO(HttpStatus.BAD_REQUEST, "2000", "식재료가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    @Override
    public ApiResponse<Void> getErrorResponse() {
        return null;
    }
}
