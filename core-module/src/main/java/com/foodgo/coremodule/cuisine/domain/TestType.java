package com.foodgo.coremodule.cuisine.domain;

public enum TestType {

    SOCIAL("사회적 미식가"),
    ENERGY("에너지 운동가"),
    ADVENTUROUS("모험심 가득한 미식가"),
    HEALTHY("건강한 미식가"),
    CONVENIENT("간편 요리사")
    ;

    private final String description;

    TestType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

