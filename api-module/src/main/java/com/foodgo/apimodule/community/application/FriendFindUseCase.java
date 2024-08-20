package com.foodgo.apimodule.community.application;

import com.foodgo.apimodule.community.dto.FriendRequestList;
import com.foodgo.apimodule.community.dto.FriendSearchList;
import com.foodgo.coremodule.community.domain.Challenge;
import com.foodgo.coremodule.community.domain.Friendship;
import com.foodgo.coremodule.community.service.ChallengeQueryService;
import com.foodgo.coremodule.community.service.FriendQueryService;
import com.foodgo.coremodule.user.domain.User;
import com.foodgo.coremodule.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendFindUseCase {

    private final UserQueryService userQueryService;
    private final FriendQueryService friendQueryService;
    private final ChallengeQueryService challengeQueryService;

    public List<FriendSearchList> findFriendList(User myUser) {

        // 전체 친구 목록을 가져옴
        List<User> users = userQueryService.findAllUsers();

        return users.stream()
                .filter(user -> friendQueryService.checkFriendShipYn(myUser.getId(), user.getId()))
                .map(user -> {

                    Challenge challenge = challengeQueryService.findRecentChallenge(user.getId());
                    if (challenge == null) {
                        challenge = Challenge.builder()
                                .totalCalorie(0)
                                .carbRate(0)
                                .proteinRate(0)
                                .fatRate(0)
                                .year(0)
                                .month(0)
                                .date(0)
                                .build();
                    }

                    Friendship friendship = friendQueryService.findByUserIdAndFriendId(myUser.getId(), user.getId());
                    if (friendship == null) {
                        friendship = Friendship.createFriendship(myUser, user);
                    }

                    Boolean challengeYn = challengeQueryService.checkChallengeYn(friendship.getId());

                    return new FriendSearchList(
                            user.getId(),
                            user.getUsername(),
                            user.getImageUrl(),
                            challenge.getTotalCalorie(),
                            challenge.getCarbRate(),
                            challenge.getProteinRate(),
                            challenge.getFatRate(),
                            true,
                            friendship.getIsMutual(),
                            challengeYn
                    );
                }).toList();
    }

    public List<FriendSearchList> findFriendName(User myUser, String nickname) {

        List<User> users = friendQueryService.findFriendWithNickname(nickname);

        return users.stream()
                .filter(user -> friendQueryService.checkFriendShipYn(myUser.getId(), user.getId()))
                .map(user -> {

                    Challenge challenge = challengeQueryService.findRecentChallenge(user.getId());
                    if (challenge == null) {
                        challenge = Challenge.builder()
                                .totalCalorie(0)
                                .carbRate(0)
                                .proteinRate(0)
                                .fatRate(0)
                                .year(0)
                                .month(0)
                                .date(0)
                                .build();
                    }

                    Friendship friendship = friendQueryService.findByUserIdAndFriendId(myUser.getId(), user.getId());
                    if (friendship == null) {
                        friendship = Friendship.createFriendship(myUser, user);
                    }

                    Boolean challengeYn = challengeQueryService.checkChallengeYn(friendship.getId());

                    return new FriendSearchList(
                            user.getId(),
                            user.getUsername(),
                            user.getImageUrl(),
                            challenge.getTotalCalorie(),
                            challenge.getCarbRate(),
                            challenge.getProteinRate(),
                            challenge.getFatRate(),
                            true,
                            friendship.getIsMutual(),
                            challengeYn
                    );
                }).toList();
    }

    public List<FriendRequestList> findFriendRequestList(Long userId) {

        return friendQueryService.findFriendRequestList(userId).stream()
                .map(user -> new FriendRequestList(user.getId(), user.getUsername()))
                .collect(Collectors.toList());
    }

}
