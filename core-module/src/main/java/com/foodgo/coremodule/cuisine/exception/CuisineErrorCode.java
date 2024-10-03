package com.foodgo.coremodule.cuisine.exception;

import com.foodgo.commonmodule.common.ApplicationResponse;
import com.foodgo.commonmodule.common.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CuisineErrorCode implements BaseErrorCode {

    NO_INGREDIENT_INFO(HttpStatus.BAD_REQUEST, "2000", "식재료가 존재하지 않습니다."),
    OPEN_API_INFO_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "3000", "OPEN API 호출에 실패했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    @Override
    public ApplicationResponse<Void> getErrorResponse() {
        return null;
    }
}
