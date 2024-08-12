package com.foodgo.coremodule.community.domain;

import com.foodgo.commonmodule.common.BaseEntity;
import com.foodgo.coremodule.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "friendship")
public class Friendship extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendship_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "friend_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User friend;

    @Column(name = "is_mutual", nullable = false)
    private Boolean isMutual;

    public static Friendship createFriendship(User user, User friend) {
        return Friendship.builder()
                .user(user)
                .friend(friend)
                .isMutual(false)
                .build();
    }

    public void markAsMutual() {
        this.isMutual = true;
    }
}

