package com.foodgo.apimodule.user.presentation;

import com.foodgo.apimodule.user.application.UserQueryService;
import com.foodgo.apimodule.user.application.UserService;
import com.foodgo.commonmodule.exception.jwt.dto.JwtDto;
import com.foodgo.apimodule.user.annotation.UserResolver;
import com.foodgo.coremodule.user.domain.User;
import com.foodgo.apimodule.user.dto.request.UserRegisterRequest;
import com.foodgo.apimodule.user.dto.response.UserRegisterResponse;
import com.foodgo.commonmodule.exception.common.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Validated
@Tag(name = "User", description = "User 관련 API")
public class UserController {

    private final UserService userService;
    private final UserQueryService userQueryService;

    @PostMapping("/join")
    public ApiResponse<UserRegisterResponse> register(@Valid @RequestBody UserRegisterRequest request) {
        return ApiResponse.onSuccess(userService.register(request));
    }

    @GetMapping("/reissue")
    public ApiResponse<JwtDto> reissueToken(@RequestHeader("RefreshToken") String refreshToken) {
        return ApiResponse.onSuccess(userService.reissueToken(refreshToken));
    }

    @GetMapping("/test")
    public ApiResponse<String> register(@UserResolver User user) {
        return ApiResponse.onSuccess(user.getUsername());
    }
}
