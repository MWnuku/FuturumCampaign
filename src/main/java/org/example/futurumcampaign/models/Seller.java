package org.example.futurumcampaign.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "seller")
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Seller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Long id;
	@Column(nullable = false)
	private String companyName;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String lastName;
	@Column
	private Double balance;
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIdentityReference(alwaysAsId = true)
	private List<Campaign> campaigns;

	public Seller(String companyName, String name, String lastName, Double balance, List<Campaign> campaigns) {
		this.companyName = companyName;
		this.name = name;
		this.lastName = lastName;
		this.balance = balance;
		this.campaigns = campaigns;
	}
}
