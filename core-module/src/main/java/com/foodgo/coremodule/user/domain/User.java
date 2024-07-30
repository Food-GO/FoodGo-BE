package com.foodgo.coremodule.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodgo.commonmodule.common.BaseEntity;
import com.foodgo.coremodule.user.enums.RoleType;
import com.foodgo.coremodule.user.enums.UserStatus;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_image_url")
    private String imageUrl;

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
