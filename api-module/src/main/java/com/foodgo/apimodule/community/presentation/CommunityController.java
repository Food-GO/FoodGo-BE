package com.foodgo.apimodule.community.presentation;

import com.foodgo.apimodule.community.application.FriendFindUseCase;
import com.foodgo.apimodule.community.application.FriendSaveUseCase;
import com.foodgo.apimodule.community.dto.FriendRequestList;
import com.foodgo.apimodule.community.dto.FriendSearchList;
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

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/community")
@Validated
@Tag(name = "community", description = "커뮤니티 관련 API")
public class CommunityController {

    private final FriendFindUseCase friendFindUseCase;
    private final FriendSaveUseCase friendSaveUseCase;

    // 친구 목록 조회 - (공동 목표까지 포함)
    @GetMapping("/friend")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "친구 목록 조회 - 공동 목표까지 포함",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "친구 목록 조회 API", description = "친구 목록 조회 API 입니다.")
    public ApplicationResponse<List<FriendSearchList>> findFriendList(
            @UserResolver User user
    ) {

        List<FriendSearchList> friendSearchLists = friendFindUseCase.findFriendList(user);
        return ApplicationResponse.onSuccess(friendSearchLists);
    }

    // 닉네임으로 친구 검색
    @GetMapping("/friend/search/{nickname}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "친구 검색 - 닉네임",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "친구 검색 API", description = "친구 검색 API 입니다. - 닉네임")
    public ApplicationResponse<List<FriendSearchList>> findFriendName(
            @UserResolver User user,
            @PathVariable String nickname
    ) {

        List<FriendSearchList> searchLists = friendFindUseCase.findFriendName(user, nickname);
        return ApplicationResponse.onSuccess(searchLists);
    }

    // 친구 신청하기
    @PostMapping("/friend/request/{friendId}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "친구 요청",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "친구 요청 API", description = "친구 요청 API 입니다.")
    public ApplicationResponse<String> requestFriend(
            @UserResolver User user,
            @PathVariable Long friendId
    ) {

        Boolean result = friendSaveUseCase.requestFriend(user, friendId);
        if (result) {
            return ApplicationResponse.onSuccess("친구 신청하였습니다.");
        } else {
            return ApplicationResponse.onFailure("400", "이미 신청한 친구입니다.");
        }
    }

    // 친구 삭제하기
    @DeleteMapping("/friend/request/{friendId}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "친구 삭제",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "친구 삭제 API", description = "친구 삭제 API 입니다.")
    public ApplicationResponse<String> deleteFriendship(
            @UserResolver User user,
            @PathVariable Long friendId
    ) {

        friendSaveUseCase.deleteFriend(user, friendId);
        return ApplicationResponse.onSuccess("친구 관계가 삭제되었습니다.");
    }

    // 친구 요청 리스트
    @GetMapping("/friend/request")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "친구 요청 리스트",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "친구 요청 리스트 API", description = "친구 요청 리스트 API 입니다.")
    public ApplicationResponse<List<FriendRequestList>> findFriendRequestList(
            @UserResolver User user
    ) {

        List<FriendRequestList> requestLists = friendFindUseCase.findFriendRequestList(user.getId());
        return ApplicationResponse.onSuccess(requestLists);
    }

    // 친구 수락하기
    @PatchMapping("/friend/accept/{friendId}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "친구 수락하기",
                            useReturnTypeSchema = true
                    )
            }
    )
    @Operation(summary = "친구 수락 API", description = "친구 수락 API 입니다.")
    public ApplicationResponse<String> acceptFriend(
            @UserResolver User user,
            @PathVariable Long friendId
    ) {

        friendSaveUseCase.acceptFriendRequest(user.getId(), friendId);
        return ApplicationResponse.onSuccess("친구 수락하였습니다.");
    }

}
