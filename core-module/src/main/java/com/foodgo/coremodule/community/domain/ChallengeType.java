package com.foodgo.coremodule.community.domain;

public enum ChallengeType {
    CALORIE("칼로리"),
    CARB("탄수화물"),
    PROTEIN("단백질"),
    FAT("지방"),
    FREQUENCY("식사주기"),
    NONE("없음")
    ;

    private final String description;

    ChallengeType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
