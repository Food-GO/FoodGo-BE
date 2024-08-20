package com.foodgo.coremodule.community.service;

import com.foodgo.coremodule.community.domain.Challenge;
import com.foodgo.coremodule.community.repository.ChallengeDetailRepository;
import com.foodgo.coremodule.community.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengeQueryService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeDetailRepository challengeDetailRepository;

    public Challenge findRecentChallenge(Long userId) {
        return challengeRepository.findTopByFriendshipUserIdOrderByCreatedAtDesc(userId);
    }

    public Boolean checkChallengeYn(Long friendshipId) {
        return challengeRepository.existsChallengeByFriendshipId(friendshipId);
    }
}
