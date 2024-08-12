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

    @Column(name = "challenge_description")
    private String description;
}
