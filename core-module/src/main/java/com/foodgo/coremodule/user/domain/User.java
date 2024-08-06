package com.foodgo.coremodule.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodgo.commonmodule.common.BaseEntity;
import com.foodgo.coremodule.user.enums.DiseaseType;
import com.foodgo.coremodule.user.enums.RoleType;
import com.foodgo.coremodule.user.enums.UsageType;
import com.foodgo.coremodule.user.enums.UserStatus;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_username", nullable = false, unique = true)
    private String username;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_nickname", nullable = false)
    private String nickname;

    @Column(name = "user_image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_usagetype", nullable = false)
    private UsageType usageType;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_diseasetype", nullable = false)
    private DiseaseType diseaseType;

    @Column(name = "user_lifestyle", nullable = false)
    private String lifeStyle;

    @Column(name = "user_roletype", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(name = "user_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    public void deactivate() {
        this.userStatus = UserStatus.INACTIVE;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void updatePassword(String password) {
        this.password = password == null ? this.password : password;
    }

    public void update(String imageUrl) {
        this.imageUrl = imageUrl == null ? this.imageUrl : imageUrl;
    }
}
