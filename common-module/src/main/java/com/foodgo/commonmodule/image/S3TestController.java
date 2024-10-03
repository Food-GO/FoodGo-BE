package com.foodgo.commonmodule.image;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.foodgo.commonmodule.common.ApplicationResponse;
import com.foodgo.commonmodule.image.service.AwsS3Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/test")
public class S3TestController {

	private final AwsS3Service awsS3Service;

	@PostMapping(value = "/uploadFile", consumes = "multipart/form-data")
	public ApplicationResponse<String> uploadFile(@RequestPart(value = "file", required = false) MultipartFile file) {
		return ApplicationResponse.onSuccess(awsS3Service.uploadFile(file));
	}
}
