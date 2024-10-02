package com.foodgo.coremodule.community.domain;

import com.foodgo.commonmodule.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "challenges")
public class Challenge extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    private Long id;

    @Column(name = "challenge_type", nullable = false)
    private ChallengeType type;

    @Column(name = "challenge_value", nullable = false)
    private Integer value;

    @Column(name = "challenge_year", nullable = false)
    private Integer year;

    @Column(name = "challenge_month", nullable = false)
    private Integer month;

    @Column(name = "challenge_date", nullable = false)
    private Integer date;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "friendship_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Friendship friendship;

}
