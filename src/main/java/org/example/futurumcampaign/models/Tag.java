package org.example.futurumcampaign.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tag")
@Getter
@Setter
@NoArgsConstructor
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Long id;
	@Column(nullable = false)
	private KeywordEnum keyword;

	public Tag(KeywordEnum keywordEnum) {
		this.keyword = keywordEnum;
	}
}
