package com.kolev.radmil.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kolev.radmil.entity.Game;
import com.kolev.radmil.entity.Player;
import com.kolev.radmil.entity.Set;
import com.kolev.radmil.form.GameWrapper;
import com.kolev.radmil.service.GameService;
import com.kolev.radmil.service.PlayerService;
import com.kolev.radmil.service.SetService;

@Controller
public class GameController {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private GameService gameService;

	@Autowired
	private SetService setService;

	@RequestMapping(value = "/showAllGames")
	public String showAllGames(Model model) {
		List<GameWrapper> gamesWrappers = new ArrayList<GameWrapper>();
		List<Game> games = gameService.getAllGames();
		for (Game game : games) {
			GameWrapper gameWrapper = new GameWrapper();
			gameWrapper.setPlayer1(game.getPlayer1());
			gameWrapper.setPlayer2(game.getPlayer2());
			gameWrapper.setDate(game.getDate());
			gameWrapper.setSets(game.getSets());
			gamesWrappers.add(gameWrapper);
		}
		model.addAttribute("gamesList", gamesWrappers);
		return "showAllGames";
	}

	@RequestMapping(value = "/showPlayerGames", method = RequestMethod.GET)
	public ModelAndView showPlayerGamesForm() {
		return new ModelAndView("showPlayerGames", "player", new Player());
	}

	@RequestMapping(value = "/showPlayerGames", method = RequestMethod.POST)
	public String showPlayerGamesSubmit(@ModelAttribute Player player, Model model) {
		Player searchedPlayer = playerService.getPlayerByName(player.getName());
		if (searchedPlayer == null) {
			model.addAttribute("player", player);
			return "pnf";
		}
		List<GameWrapper> allPlayerGames = new ArrayList<GameWrapper>();
		List<Game> games = gameService.getGamesByPlayer(searchedPlayer);
		for (Game game : games) {
			GameWrapper gameWrapper = new GameWrapper();
			gameWrapper.setDate(game.getDate());
			gameWrapper.setSets(setService.getSetsByGame(game));
			if (game.getPlayer1().getCardId() == searchedPlayer.getCardId()) {
				gameWrapper.setPlayer1(game.getPlayer1());
				gameWrapper.setPlayer2(game.getPlayer2());
			} else {
				gameWrapper.setPlayer1(game.getPlayer2());
				gameWrapper.setPlayer2(game.getPlayer1());
				List<Set> sets = gameWrapper.getSets();
				for (int i = 0; i < sets.size() / 2; i++) {
					sets.add(0, sets.get(sets.size() - 1));
					sets.remove(sets.size() - 1);
				}
			}
			allPlayerGames.add(gameWrapper);
		}
		model.addAttribute("gamesList", allPlayerGames);
		return "showAllGames";
	}

	@RequestMapping(value = "/newGame", method = RequestMethod.GET)
	public ModelAndView gameForm() {
		return new ModelAndView("newGame", "gameWrapper", new GameWrapper());
	}

	@RequestMapping(value = "/newGame", method = RequestMethod.POST)
	public String gameSubmit(@ModelAttribute GameWrapper gameWrapper, Model model) {
		Player player1 = playerService.getPlayer(gameWrapper.getPlayer1().getCardId());
		Player player2 = playerService.getPlayer(gameWrapper.getPlayer2().getCardId());
		if (player1 == null) {
			model.addAttribute("player", player1);
			return "pnf";
		}
		if (player2 == null) {
			model.addAttribute("player", player2);
			return "pnf";
		}
		gameWrapper.setPlayer1(player1);
		gameWrapper.setPlayer2(player2);
		gameService.addGame(new Game(), gameWrapper);
		model.addAttribute("gameWrapper", gameWrapper);
		return "newGameResult";
	}
}
