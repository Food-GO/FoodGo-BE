package com.foodgo.apimodule.community.mapper;

import com.foodgo.apimodule.community.dto.ChallengeList;
import com.foodgo.apimodule.community.dto.MakeChallenge;
import com.foodgo.coremodule.community.domain.Challenge;
import com.foodgo.coremodule.community.domain.Friendship;

public class ChallengeMapper {

    public static Challenge toEntity(MakeChallenge dto, Friendship friendship) {
        if (dto == null) {
            return null;
        }

        return Challenge.builder()
                .type(dto.type())
                .value(dto.value())
                .year(dto.year())
                .month(dto.month())
                .date(dto.date())
                .friendship(friendship)
                .build();
    }

    public static ChallengeList toDto(Challenge entity) {
        if (entity == null) {
            return null;
        }

        return new ChallengeList(
                entity.getId(),
                entity.getType(),
                entity.getValue(),
                entity.getYear(),
                entity.getMonth(),
                entity.getDate(),
                "0",
                "0"
        );
    }
}

