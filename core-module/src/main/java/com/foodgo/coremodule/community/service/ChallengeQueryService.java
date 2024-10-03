package com.foodgo.coremodule.community.service;

import com.foodgo.coremodule.community.domain.Challenge;
import com.foodgo.coremodule.community.exception.ChallengeErrorCode;
import com.foodgo.coremodule.community.exception.ChallengeException;
import com.foodgo.coremodule.community.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengeQueryService {

    private final ChallengeRepository challengeRepository;

    public Challenge findChallengeById(Long challengeId) {
        return challengeRepository.findById(challengeId)
                .orElseThrow(() -> new ChallengeException(ChallengeErrorCode.NO_CHALLENGE_INFO));
    }

    public Challenge findRecentChallenge(Long userId) {
        return challengeRepository.findTopByFriendshipUserIdOrderByCreatedAtDesc(userId);
    }

    public Boolean checkChallengeYn(Long friendshipId) {
        return challengeRepository.existsChallengeByFriendshipId(friendshipId);
    }

    public void saveChallenge(Challenge challenge) {
        challengeRepository.save(challenge);
    }

    public void deleteChallenge(Long challengeId) {
        challengeRepository.deleteById(challengeId);
    }
}
