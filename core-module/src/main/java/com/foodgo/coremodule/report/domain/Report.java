package com.foodgo.coremodule.report.domain;

import com.foodgo.commonmodule.common.BaseEntity;
import com.foodgo.coremodule.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "report")
public class Report extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @Column(name = "report_type", nullable = false)
    private MealType type;

    @Column(name = "report_total", nullable = false)
    private Integer total;

    @Column(name = "report_carb", nullable = false)
    private Integer carb;

    @Column(name = "report_protein", nullable = false)
    private Integer protein;

    @Column(name = "report_fat", nullable = false)
    private Integer fat;

    @Column(name = "report_sugar", nullable = false)
    private Integer sugar;      // 당류

    @Column(name = "report_sodium", nullable = false)
    private Integer sodium;     // 나트륨

    @Column(name = "report_cholesterol", nullable = false)
    private Integer cholesterol; // 콜레스테롤

    @Column(name = "report_saturated_fat", nullable = false)
    private Integer saturatedFat; // 포화지방산

    @Column(name = "report_trans_fat", nullable = false)
    private Integer transFat;     // 트랜스지방
}
