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
    private Double total;

    @Column(name = "report_carb", nullable = false)
    private Double carb;

    @Column(name = "report_protein", nullable = false)
    private Double protein;

    @Column(name = "report_fat", nullable = false)
    private Double fat;

    @Column(name = "report_sugar", nullable = false)
    private Double sugar;      // 당류

    @Column(name = "report_sodium", nullable = false)
    private Double sodium;     // 나트륨

    @Column(name = "report_cholesterol", nullable = false)
    private Double cholesterol; // 콜레스테롤

    @Column(name = "report_saturated_fat", nullable = false)
    private Double saturatedFat; // 포화지방산

    @Column(name = "report_trans_fat", nullable = false)
    private Double transFat;     // 트랜스지방
}
