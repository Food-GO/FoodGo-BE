package com.foodgo.apimodule.user.presentation;

import com.foodgo.apimodule.user.application.LoginUseCase;
import com.foodgo.apimodule.user.dto.LoginRequest;
import com.foodgo.coremodule.user.dto.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
@Validated
@Tag(name = "User", description = "User 관련 API")
public class UserController {

    private final LoginUseCase loginUseCase;

    @PostMapping("/login/sso")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "로그인 성공",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "로그인 API", description = "로그인 API입니다.")
    public LoginResponse ssoLogin(@Valid @RequestBody LoginRequest loginRequest) {
        return loginUseCase.ssoLogin(loginRequest);
    }
}
