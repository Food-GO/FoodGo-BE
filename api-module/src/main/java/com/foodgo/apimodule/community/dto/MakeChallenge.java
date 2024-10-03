package com.foodgo.apimodule.community.dto;

import com.foodgo.coremodule.community.domain.ChallengeType;

public record MakeChallenge(
        Long friendId,
        ChallengeType type,
        Integer value,
        Integer year,
        Integer month,
        Integer date
) {
}
