package com.foodgo.coremodule.security.filter;

import com.foodgo.commonmodule.common.ApplicationResponse;
import com.foodgo.commonmodule.common.BaseErrorCode;
import com.foodgo.commonmodule.security.util.HttpResponseUtil;
import com.foodgo.coremodule.security.jwt.exception.SecurityCustomException;
import com.foodgo.coremodule.security.jwt.exception.SecurityErrorCode;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(
		@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response,
		@NonNull FilterChain filterChain) throws IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (SecurityCustomException e) {
			log.warn("[*] SecurityCustomException : ", e);
			BaseErrorCode errorCode = e.getErrorCode();
			ApplicationResponse<String> errorResponse = ApplicationResponse.onFailure(
				errorCode.getCode(),
				errorCode.getMessage(),
				e.getMessage()
			);
			HttpResponseUtil.setErrorResponse(
				response,
				errorCode.getHttpStatus(),
				errorResponse
			);
		} catch (Exception e) {
			log.error("[*] Exception : ", e);
			ApplicationResponse<String> errorResponse = ApplicationResponse.onFailure(
				SecurityErrorCode.INTERNAL_SECURITY_ERROR.getCode(),
				SecurityErrorCode.INTERNAL_SECURITY_ERROR.getMessage(),
				e.getMessage()
			);
			HttpResponseUtil.setErrorResponse(
				response,
				HttpStatus.INTERNAL_SERVER_ERROR,
				errorResponse
			);
		}
	}
}
