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

    @Column(name = "challenge_name", nullable = false)
    private String name;

    @Column(name = "challenge_calorie", nullable = false)
    private Integer totalCalorie;

    @Column(name = "challenge_carb_rate", nullable = false)
    private Integer carbRate;

    @Column(name = "challenge_protein_rate", nullable = false)
    private Integer proteinRate;

    @Column(name = "challenge_fat_rate", nullable = false)
    private Integer fatRate;

    @Column(name = "challenge_year", nullable = false)
    private Integer year;

    @Column(name = "challenge_month", nullable = false)
    private Integer month;

    @Column(name = "challenge_date", nullable = false)
    private Integer date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friendship_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Friendship friendship;
}
