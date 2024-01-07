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
import java.util.Objects;

@Entity
@Table(name = "campaign")
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Campaign{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Long id;
	@Column(nullable = false)
	private String name;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@Column(nullable = false)
	private List<Tag> tags;
	@Column(nullable = false)
	private Double bidAmount;
	@Column(nullable = false)
	private Status status;
	@Column
	private Town town;
	@Column(nullable = false)
	private Double radius;
	@ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn()
	private Seller seller;

	public Campaign(String name, List<Tag> tags, Double bidAmount, Status status, Town town, Double radius, Seller seller){
		this.name = name;
		this.tags = tags;
		this.bidAmount = bidAmount;
		this.status = status;
		this.town = town;
		this.radius = radius;
		this.seller = seller;
	}

	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		Campaign campaign = (Campaign) o;
		return Objects.equals(id, campaign.id);
	}

	@Override
	public int hashCode(){
		return Objects.hash(id);
	}
}
