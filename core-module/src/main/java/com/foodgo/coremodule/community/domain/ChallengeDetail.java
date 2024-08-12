package com.foodgo.coremodule.community.domain;

import com.foodgo.commonmodule.common.BaseEntity;
import com.foodgo.coremodule.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "challenge_deail")
public class ChallengeDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @Column(name = "target_value", nullable = false)
    private Integer targetValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friendship_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Friendship friendship;
}

