package com.foodgo.apimodule.community.presentation;

import com.foodgo.apimodule.community.application.FriendFindUseCase;
import com.foodgo.apimodule.community.application.FriendSaveUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/community")
@Validated
@Tag(name = "community", description = "커뮤니티 관련 API")
public class CommunityController {

    private final FriendFindUseCase friendFindUseCase;
    private final FriendSaveUseCase friendSaveUseCase;

    // 친구 목록 조회 - (공동 목표까지 포함)

    // 아이디로 친구 검색

    // 친구 신청하기

    // 친구 삭제하기

    // 친구 요청 리스트

    // 친구 수락하기

}
