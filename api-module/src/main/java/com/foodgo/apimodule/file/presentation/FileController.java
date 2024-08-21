package com.foodgo.apimodule.file.presentation;

import com.foodgo.commonmodule.common.ApplicationResponse;
import com.foodgo.commonmodule.image.dto.PresignedUrlResponse;
import com.foodgo.commonmodule.image.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "파일 관리 API", description = "파일 업로드 및 다운로드 관련 API")
@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @Operation(summary = "프리사인드 URL 생성", description = "파일 업로드를 위한 프리사인드 URL을 생성합니다. 유효기간 15분 입니다.")
    @GetMapping("/presigned-url")
    public ApplicationResponse<PresignedUrlResponse> getPresignedUrl(
            @Parameter(description = "파일 경로의 prefix (이미지의 경우 images 필수)", example = "/images") @RequestParam(name = "prefix", defaultValue = "images") String prefix,
            @Parameter(description = "파일 이름", required = true, example = "example.txt") @RequestParam(name = "fileName") String fileName) {
        return ApplicationResponse.ok(fileService.getUploadPresignedUrl(prefix, fileName));
    }
}