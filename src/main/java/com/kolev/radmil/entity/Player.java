package com.kolev.radmil.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "player")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer cardId;

	@Size(min = 2, max = 30, message = "Player name should be between 2 and 30 characters long!")
	@Column(unique = true)
	private String name;

	@NotNull(message = "Input player rank!")
	@Min(value = 1, message = "Player rank should be between 1 and 2000!")
	@Max(value = 2000, message = "Player rank should be between 1 and 2000!")
	@Column
	private Integer rank;

	@NotNull(message = "Input player age!")
	@Min(value = 14, message = "Player age should be between 14 and 50!")
	@Max(value = 50, message = "Player age should be between 14 and 50!")
	@Column
	private Integer age;

	@NotEmpty(message = "Input player country!")
	@Column
	private String country;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

}
