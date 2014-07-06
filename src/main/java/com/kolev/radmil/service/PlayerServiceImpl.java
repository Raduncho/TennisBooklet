package com.kolev.radmil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kolev.radmil.entity.Player;
import com.kolev.radmil.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository repository;

	@Override
	@Transactional
	public void addPlayer(Player player) {
		repository.save(player);
	}

	@Override
	@Transactional
	public void removePlayer(int cardId) {
		repository.delete(cardId);
	}

	@Override
	@Transactional
	public Player getPlayer(int cardId) {
		return repository.findOne(cardId);
	}

	@Override
	@Transactional
	public List getAllPlayers() {
		return repository.findAll();
	}

}
