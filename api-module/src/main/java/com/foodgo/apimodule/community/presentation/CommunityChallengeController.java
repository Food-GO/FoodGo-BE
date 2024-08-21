package com.foodgo.apimodule.community.presentation;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/community/challenge")
@Validated
@Tag(name = "community - challenge", description = "커뮤니티 챌린지 관련 API")
public class CommunityChallengeController {

    // 챌린지 생성

    // 챌린지 삭제

    // 챌린지 조회 (달성률 포함)
}
