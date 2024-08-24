package com.foodgo.apimodule.community.application;

import com.foodgo.apimodule.community.dto.MakeChallenge;
import com.foodgo.apimodule.community.mapper.ChallengeMapper;
import com.foodgo.coremodule.community.domain.Challenge;
import com.foodgo.coremodule.community.domain.Friendship;
import com.foodgo.coremodule.community.service.ChallengeQueryService;
import com.foodgo.coremodule.community.service.FriendQueryService;
import com.foodgo.coremodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChallengeSaveUseCase {

    private final ChallengeQueryService challengeQueryService;
    private final FriendQueryService friendQueryService;

    public void saveChallenge(MakeChallenge makeChallenge, User user) {

        Friendship friendship = friendQueryService.findByUserIdAndFriendId(user.getId(), makeChallenge.friendId());
        final Challenge challenge = ChallengeMapper.toEntity(makeChallenge, friendship);

        challengeQueryService.saveChallenge(challenge);
    }
}
