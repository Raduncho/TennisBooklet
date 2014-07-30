package com.kolev.radmil.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer gameId;

	@Column
	private Date date;

	@ManyToOne
	@JoinColumn(name = "player1Id", referencedColumnName = "cardId")
	private Player player1;

	@ManyToOne
	@JoinColumn(name = "player2Id", referencedColumnName = "cardId")
	private Player player2;

	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
	private List<Set> sets;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public List<Set> getSets() {
		return sets;
	}

	public void setSets(List<Set> sets) {
		this.sets = sets;
	}

	public void addSet(Set set) {
		if (sets == null) {
			sets = new ArrayList<Set>();
		}
		sets.add(set);
	}

}
