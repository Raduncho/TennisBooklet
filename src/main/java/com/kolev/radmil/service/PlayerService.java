package com.kolev.radmil.service;

import java.util.List;

import com.kolev.radmil.entity.Player;

public interface PlayerService {
	public void addPlayer(Player player);
	public void removePlayer(int cardId);
	public Player getPlayer(int cardId);
	public List getAllPlayers();
}
