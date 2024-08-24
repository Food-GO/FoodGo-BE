package com.foodgo.apimodule.community.application;

import com.foodgo.apimodule.community.dto.ChallengeList;
import com.foodgo.apimodule.community.mapper.ChallengeMapper;
import com.foodgo.coremodule.community.domain.Challenge;
import com.foodgo.coremodule.community.service.ChallengeQueryService;
import com.foodgo.coremodule.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChallengeFindUseCase {

    private final ChallengeQueryService challengeQueryService;

    public ChallengeList findChallengeList(User user, Long challengeId) {

        Challenge challenge = challengeQueryService.findChallengeById(challengeId);
        return ChallengeMapper.toDto(challenge);
    }
}
