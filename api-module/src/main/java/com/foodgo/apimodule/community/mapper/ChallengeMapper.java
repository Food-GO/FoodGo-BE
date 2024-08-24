package com.foodgo.apimodule.community.mapper;

import com.foodgo.apimodule.community.dto.MakeChallenge;
import com.foodgo.coremodule.community.domain.Challenge;
import com.foodgo.coremodule.community.domain.Friendship;

public class ChallengeMapper {

    public static Challenge toEntity(MakeChallenge dto, Friendship friendship) {
        if (dto == null) {
            return null;
        }

        return Challenge.builder()
                .name(dto.name())
                .type(dto.type())
                .totalCalorie(dto.calorie())
                .carbRate(dto.carbRate())
                .proteinRate(dto.proteinRate())
                .fatRate(dto.fatRate())
                .year(dto.year())
                .month(dto.month())
                .date(dto.date())
                .friendship(friendship)
                .build();
    }

    public static MakeChallenge toDto(Challenge entity, Long friendId) {
        if (entity == null) {
            return null;
        }

        return new MakeChallenge(
                friendId,
                entity.getName(),
                entity.getType(),
                entity.getTotalCalorie(),
                entity.getCarbRate(),
                entity.getProteinRate(),
                entity.getFatRate(),
                entity.getYear(),
                entity.getMonth(),
                entity.getDate()
        );
    }
}

