package com.foodgo.apimodule.community.dto;

import com.foodgo.coremodule.community.domain.ChallengeType;

public record MakeChallenge(
        Long friendId,
        String name,
        ChallengeType type,
        Integer calorie,
        Integer carbRate,
        Integer proteinRate,
        Integer fatRate,
        Integer year,
        Integer month,
        Integer date
) {
}
