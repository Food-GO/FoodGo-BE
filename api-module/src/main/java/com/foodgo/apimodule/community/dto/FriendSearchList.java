package com.foodgo.apimodule.community.dto;

public record FriendSearchList(
        Long userId,
        String name,
        String profileImg,
        Integer recentTotalCalorie,
        Integer recentCarbRate,
        Integer recentProteinRate,
        Integer recentFatRate,
        Boolean friendYn,
        Boolean friendRequestYn,
        Boolean challengeYn
) {
}
