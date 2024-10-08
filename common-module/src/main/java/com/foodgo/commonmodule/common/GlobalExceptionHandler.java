package com.foodgo.commonmodule.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({CustomException.class})
	public ResponseEntity<ApplicationResponse<Void>> handleCustomException(CustomException e) {
		BaseErrorCode errorCode = e.getErrorCode();
		log.warn(">>>>> Custom Exception : {}", errorCode.getMessage());
		return ResponseEntity.status(errorCode.getHttpStatus()).body(errorCode.getErrorResponse());
	}

	@ExceptionHandler({DataIntegrityViolationException.class})
	public ApplicationResponse<Object> handleIntegrityConstraint(DataIntegrityViolationException e) {
		log.warn(">>>>> Data Integrity Violation Exception : {}", e.getMessage());
		// DB 에 들어갈 값이 잘못됨
		// BaseErrorCode errorStatus = OrganizationErrorCode.ORGANIZATION_ALREADY_EXIST;
		BaseErrorCode errorStatus = GlobalErrorCode.VALIDATION_FAILED;
		return ApplicationResponse.onFailure(
			errorStatus.getCode(),
			errorStatus.getMessage()
		);
	}

	// @Valid 통해 MethodArgumentNotValidException 감지
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ApplicationResponse<Map<String, String>>> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException ex
	) {
		// 실패한 validation 을 담을 Map
		Map<String, String> failedValidations = new HashMap<>();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		// fieldErrors 를 순회하며 failedValidations 에 담는다.
		fieldErrors.forEach(error -> failedValidations.put(error.getField(), error.getDefaultMessage()));
		ApplicationResponse<Map<String, String>> errorResponse = ApplicationResponse.onFailure(
			GlobalErrorCode.VALIDATION_FAILED.getCode(),
			GlobalErrorCode.VALIDATION_FAILED.getMessage(),
			failedValidations);
		return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
	}

	// 그 외의 정의되지 않은 모든 예외 처리
	@ExceptionHandler({Exception.class})
	public ResponseEntity<ApplicationResponse<String>> handleAllException(Exception e) {
		log.error(">>>>> Internal Server Error : ", e);
		BaseErrorCode errorCode = GlobalErrorCode.INTERNAL_SERVER_ERROR;
		ApplicationResponse<String> errorResponse = ApplicationResponse.onFailure(
			errorCode.getCode(),
			errorCode.getMessage(),
			e.getMessage()
		);
		return ResponseEntity
			.status(errorCode.getHttpStatus())
			.body(errorResponse);
	}
}
