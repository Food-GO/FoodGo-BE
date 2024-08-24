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

}
