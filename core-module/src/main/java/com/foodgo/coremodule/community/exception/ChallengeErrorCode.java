package com.foodgo.coremodule.community.exception;

import com.foodgo.commonmodule.common.ApplicationResponse;
import com.foodgo.commonmodule.common.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ChallengeErrorCode implements BaseErrorCode {

    NO_CHALLENGE_INFO(HttpStatus.BAD_REQUEST, "2000", "챌린지가 존재하지 않습니다."),
    NO_CHALLENGE_TYPE(HttpStatus.BAD_REQUEST, "2000", "챌린지 타입이 존재하지 않습니다."),
    NOT_CHALLENGE_USER(HttpStatus.BAD_REQUEST, "2000", "본인이 생성한 챌린지가 아닙니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    @Override
    public ApplicationResponse<Void> getErrorResponse() {
        return null;
    }
}
