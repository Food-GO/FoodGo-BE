package com.foodgo.apimodule.community.presentation;

import com.foodgo.apimodule.community.application.ChallengeFindUseCase;
import com.foodgo.apimodule.community.application.ChallengeSaveUseCase;
import com.foodgo.apimodule.community.dto.ChallengeList;
import com.foodgo.apimodule.community.dto.MakeChallenge;
import com.foodgo.commonmodule.common.ApplicationResponse;
import com.foodgo.coremodule.security.annotation.UserResolver;
import com.foodgo.coremodule.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/community/challenge")
@Validated
@Tag(name = "community - challenge", description = "커뮤니티 챌린지 관련 API")
public class CommunityChallengeController {

    private final ChallengeFindUseCase challengeFindUseCase;
    private final ChallengeSaveUseCase challengeSaveUseCase;

    // 챌린지 생성
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "챌린지 생성",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "챌린지 생성 API", description = "챌린지 생성 API 입니다.")
    public ApplicationResponse<String> makeChallenge(
            @UserResolver User user,
            @RequestBody MakeChallenge makeChallenge
    ) {

        challengeSaveUseCase.saveChallenge(makeChallenge, user);
        return ApplicationResponse.onSuccess("챌린지가 생성되었습니다.");
    }

    // 챌린지 삭제
    @DeleteMapping("/{challengeId}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "챌린지 삭제",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "챌린지 삭제 API", description = "챌린지 삭제 API 입니다.")
    public ApplicationResponse<String> deleteChallenge(
            @UserResolver User user,
            @PathVariable Long challengeId
    ) {

        challengeSaveUseCase.deleteChallenge(user, challengeId);
        return ApplicationResponse.onSuccess("챌린지가 삭제되었습니다.");
    }

    // 챌린지 조회 (달성률 포함)
    @GetMapping("/{challengeId}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "챌린지 조회",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "챌린지 조회 API", description = "챌린지 조회 API 입니다.")
    public ApplicationResponse<ChallengeList> findChallengeList(
            @UserResolver User user,
            @PathVariable Long challengeId
    ) {

        ChallengeList list = challengeFindUseCase.findChallengeList(user, challengeId);
        return ApplicationResponse.onSuccess(list);
    }

}
