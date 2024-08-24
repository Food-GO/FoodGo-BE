package com.foodgo.apimodule.community.application;

import com.foodgo.apimodule.community.dto.FriendRequestList;
import com.foodgo.apimodule.community.dto.FriendSearchList;
import com.foodgo.coremodule.community.domain.Challenge;
import com.foodgo.coremodule.community.domain.ChallengeType;
import com.foodgo.coremodule.community.domain.Friendship;
import com.foodgo.coremodule.community.service.ChallengeQueryService;
import com.foodgo.coremodule.community.service.FriendQueryService;
import com.foodgo.coremodule.user.domain.User;
import com.foodgo.coremodule.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<User> users = userQueryService.findAllUsers();
        return processFriendSearchList(users, myUser);
    }

    public List<FriendSearchList> findFriendName(User myUser, String nickname) {
        List<User> users = friendQueryService.findFriendWithNickname(nickname);
        return processFriendSearchList(users, myUser);
    }

    private List<FriendSearchList> processFriendSearchList(List<User> users, User myUser) {
        return users.stream()
                .filter(user -> friendQueryService.checkFriendShipYn(myUser.getId(), user.getId()))
                .map(user -> {
                    Challenge challenge = challengeQueryService.findRecentChallenge(user.getId());
                    if (challenge == null) {
                        challenge = createDefaultChallenge();
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
                            challenge.getType(),
                            challenge.getValue(),
                            true,
                            friendship.getIsMutual(),
                            challengeYn
                    );
                }).toList();
    }

    private Challenge createDefaultChallenge() {
        return Challenge.builder()
                .type(ChallengeType.NONE)
                .value(0)
                .year(0)
                .month(0)
                .date(0)
                .build();
    }

    public List<FriendRequestList> findFriendRequestList(Long userId) {

        return friendQueryService.findFriendRequestList(userId).stream()
                .map(user -> new FriendRequestList(user.getId(), user.getUsername()))
                .collect(Collectors.toList());
    }

}
