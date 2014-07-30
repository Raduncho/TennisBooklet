package com.kolev.radmil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kolev.radmil.entity.Player;
import com.kolev.radmil.repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository repository;

	@Transactional
	public void addPlayer(Player player) {
		repository.save(player);
	}

	@Transactional
	public void removePlayer(int cardId) {
		repository.delete(cardId);
	}

	public Player getPlayer(int cardId) {
		return repository.findOne(cardId);
	}

	public List<Player> getAllPlayers() {
		return repository.findAll();
	}

	public Player getPlayerByName(String playerName) {
		return repository.findByNameLike(playerName);
	}

}
