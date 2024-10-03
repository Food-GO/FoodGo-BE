package com.foodgo.coremodule.security.jwt.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.foodgo.commonmodule.common.ApplicationResponse;
import com.foodgo.commonmodule.security.util.HttpResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 사용자가 인증되지 않은 상태에서 접근하려고 할 때 발생하는 예외 처리
 */
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException)
		throws IOException {
		HttpStatus httpStatus;
		ApplicationResponse<String> errorResponse;

		log.error("[*] AuthenticationException: ", authException);
		httpStatus = HttpStatus.UNAUTHORIZED;
		errorResponse = ApplicationResponse.onFailure(
			SecurityErrorCode.INVALID_TOKEN.getCode(),
			SecurityErrorCode.INVALID_TOKEN.getMessage(),
			authException.getMessage());

		HttpResponseUtil.setErrorResponse(response, httpStatus, errorResponse);
	}
}
