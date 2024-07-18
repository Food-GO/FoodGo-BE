package com.foodgo.coremodule.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Access(AccessType.FIELD)
public class User {

    private static final String DEFAULT_NICKNAME = "사용자";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String picture;
    private String email;


    public static User createNewUser(String name, String picture, String email) {

        User user = new User();
        user.name = name;
        user.email = email;
        user.picture = picture;
        return user;
    }
}
