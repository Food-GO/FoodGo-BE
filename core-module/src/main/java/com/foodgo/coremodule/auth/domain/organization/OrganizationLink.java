package com.foodgo.coremodule.auth.domain.organization;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "organization_link")
public class OrganizationLink {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "link_id")
	private Long id;

	@Column(name = "link_name", nullable = false)
	private String name;

	@Column(name = "link_url", nullable = false)
	private String url;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "organization_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Organization organization;

	public void update(String name, String url) {
		this.name = name == null ? this.name : name;
		this.url = url == null ? this.url : url;
	}
}
