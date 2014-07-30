package com.kolev.radmil.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kolev.radmil.entity.Game;
import com.kolev.radmil.entity.Player;
import com.kolev.radmil.form.GameWrapper;
import com.kolev.radmil.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository repository;

	@Transactional
	public void addGame(Game game, GameWrapper gameWrapper) {
		game.setDate(gameWrapper.getDate());
		game.setPlayer1(gameWrapper.getPlayer1());
		game.setPlayer2(gameWrapper.getPlayer2());
		for (int index = gameWrapper.getSets().size() - 1; index >= 0; index--) {
			if (gameWrapper.getSets().get(index).getGamesWon() == null) {
				gameWrapper.getSets().remove(index);
				continue;
			}
			gameWrapper.getSets().get(index).setGame(game);
		}
		game.setSets(gameWrapper.getSets());
		repository.save(game);
	}

	public Game getGame(int gameId) {
		return repository.findOne(gameId);
	}

	public List<Game> getAllGames() {
		return repository.findAll();
	}

	public List<Game> getGamesByPlayer(Player player) {
		return repository.findByPlayer1OrPlayer2(player, player);
	}

}
