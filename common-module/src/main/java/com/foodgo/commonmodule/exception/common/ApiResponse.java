package com.foodgo.commonmodule.exception.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"statusCode", "message", "content"})
public class ApiResponse<T> {

    // @JsonProperty("isSuccess")
    // private final Boolean isSuccess;

    @JsonProperty("statusCode")
    @NonNull
    private final String statusCode;

    @JsonProperty("message")
    @NonNull
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("content")
    private T content;

    // 성공한 경우 응답 생성
    public static <T> ApiResponse<T> onSuccess(T content) {
        // return new ApiResponse<>(true, code.getReasonHttpStatus().getCode(), code.getReasonHttpStatus().getMessage(), result);
        return new ApiResponse<>(HttpStatus.OK.name(), HttpStatus.OK.getReasonPhrase(), content);
    }

    // 실패한 경우 응답 생성
    public static <T> ApiResponse<T> onFailure(String statusCode, String message) {
        // return new ApiResponse<>(false, code, message, data);
        return new ApiResponse<>(statusCode, message, null);
    }

    public static <T> ApiResponse<T> onFailure(String statusCode, String message, T content) {
        return new ApiResponse<>(statusCode, message, content);
    }

    // Json serialize
    public String toJsonString() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }
}
