package com.foodgo.apimodule.community.dto;

import com.foodgo.coremodule.community.domain.ChallengeType;

public record ChallengeList(
        Long challengeId,
        ChallengeType type,
        Integer value,
        Integer year,
        Integer month,
        Integer date,
        Double myAchieveRate,
        Double friendAchieveRate
) {
}
