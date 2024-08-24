package com.foodgo.coremodule.community.domain;

public enum ChallengeType {
    CALORIE("칼로리"),
    NUTRIENT("영양성분"),
    FREQUENCY("식사주기")
    ;

    private final String description;

    ChallengeType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
