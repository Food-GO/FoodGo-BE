package com.foodgo.coremodule.cuisine.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import com.foodgo.coremodule.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "cuisine_test")
public class CuisineTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuisine_test_id")
    private Long id;

    @Column(name = "cuisine_test_type")
    private TestType type;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @JsonValue
    public String getTypeValue() {
        return type.getDescription();
    }
}
