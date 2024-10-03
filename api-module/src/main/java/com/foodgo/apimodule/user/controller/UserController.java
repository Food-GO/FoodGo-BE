package com.foodgo.apimodule.user.controller;

import com.foodgo.coremodule.security.annotation.UserResolver;
import com.foodgo.coremodule.security.jwt.dto.JwtDto;
import com.foodgo.coremodule.user.dto.request.PasswordUpdateRequest;
import com.foodgo.coremodule.user.dto.request.UserUpdateRequest;
import com.foodgo.coremodule.user.dto.response.UserDetailGetResponse;
import com.foodgo.coremodule.user.dto.response.UserUpdateResponse;
import com.foodgo.coremodule.user.service.UserQueryService;
import com.foodgo.coremodule.user.service.UserService;
import com.foodgo.coremodule.user.domain.User;
import com.foodgo.coremodule.user.dto.request.UserRegisterRequest;
import com.foodgo.coremodule.user.dto.response.UserRegisterResponse;
import com.foodgo.commonmodule.common.ApplicationResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Validated
@Tag(name = "User", description = "User 관련 API")
public class UserController {

    private final UserService userService;
    private final UserQueryService userQueryService;

    @PostMapping(value = "/join", consumes = "multipart/form-data")
    public ApplicationResponse<UserRegisterResponse> register(
        @RequestPart(value = "request") @Valid UserRegisterRequest request,
        @RequestPart(name = "profileImage", required = false) MultipartFile file
    ) {
        return ApplicationResponse.onSuccess(userService.register(request, file));
    }

    @GetMapping("/username")
    public ApplicationResponse<Boolean> checkUsername(@RequestParam String username) {
        return ApplicationResponse.onSuccess(userQueryService.checkUsername(username));
    }

    @GetMapping("/nickname")
    public ApplicationResponse<Boolean> checkNickname(@RequestParam String nickname) {
        return ApplicationResponse.onSuccess(userQueryService.checkNickname(nickname));
    }

    @GetMapping("/reissue")
    public ApplicationResponse<JwtDto> reissueToken(@RequestHeader("RefreshToken") String refreshToken) {
        return ApplicationResponse.onSuccess(userService.reissueToken(refreshToken));
    }

    @PatchMapping(value = "/password")
    public ApplicationResponse<String> updatePassword(
        @UserResolver User user,
        @RequestBody @Valid PasswordUpdateRequest request
    ) {
        userService.updatePassword(user, request);
        return ApplicationResponse.onSuccess("비밀번호 변경 성공");
    }

    @GetMapping("/me")
    public ApplicationResponse<UserDetailGetResponse> getMyUser(@UserResolver User authUser) {
        return ApplicationResponse.onSuccess(UserDetailGetResponse.from(authUser));
    }

    @PatchMapping(value = "/me", consumes = "multipart/form-data")
    public ApplicationResponse<UserUpdateResponse> updateMyUser(
        @UserResolver User user,
        @RequestPart @Valid UserUpdateRequest request,
        @RequestPart(value = "profileImage", required = false) MultipartFile file) {
        return ApplicationResponse.onSuccess(userService.updateMyUser(user, request, file));
    }

    @DeleteMapping("/me")
    public ApplicationResponse<String> deleteUser(@UserResolver User user) {
        userService.deactivate(user);
        return ApplicationResponse.onSuccess("탈퇴 성공");
    }
}
