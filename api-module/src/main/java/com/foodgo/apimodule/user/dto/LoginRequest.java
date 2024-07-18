package com.foodgo.apimodule.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "카카오 로그인 요청")
public record LoginRequest(

        @NotBlank(message = "이름은 필수입니다.")
        @Schema(description = "name", example = "이름")
        String name,

        @NotBlank(message = "이메일은 필수입니다.")
        @Schema(description = "email", example = "이메알")
        String email,

        @Schema(description = "picture", example = "프로필 사진")
        String picture
) {
}
