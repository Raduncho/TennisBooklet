package com.kolev.radmil.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kolev.radmil.entity.Player;
import com.kolev.radmil.entity.Set;

public class GameWrapper {

	private Player player1;
	private Player player2;
	private Date date;
	private List<Set> sets;

	public GameWrapper() {
		player1 = new Player();
		player2 = new Player();
		sets = new ArrayList<Set>();
		for (int i = 0; i < 6; i++) {
			sets.add(new Set());
		}
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Set> getSets() {
		return sets;
	}

	public void setSets(List<Set> sets) {
		this.sets = sets;
	}



}
