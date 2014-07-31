package com.kolev.radmil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kolev.radmil.entity.Game;
import com.kolev.radmil.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository repository;

	public Game addGame(Game game) {
		for (int index = game.getSets().size() - 1; index >= 0; index--) {
			if (game.getSets().get(index).getGamesWon() == null) {
				game.getSets().remove(index);
				continue;
			}
			game.getSets().get(index).setGame(game);
		}
		return repository.save(game);
	}

}
