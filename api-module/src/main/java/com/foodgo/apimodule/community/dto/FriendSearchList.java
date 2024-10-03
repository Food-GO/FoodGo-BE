package com.foodgo.apimodule.community.dto;

import com.foodgo.coremodule.community.domain.ChallengeType;

public record FriendSearchList(
        Long userId,
        String name,
        String profileImg,
        ChallengeType challengeType,
        Integer challengeValue,
        Boolean friendYn,
        Boolean friendRequestYn,
        Boolean challengeYn
) {
}
