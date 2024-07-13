package com.foodgo.coremodule.auth.domain.organization;

import com.foodgo.commonmodule.exception.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "bookmark")
public class Bookmark extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookmark_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Organization organization;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "target_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Organization target;
}
