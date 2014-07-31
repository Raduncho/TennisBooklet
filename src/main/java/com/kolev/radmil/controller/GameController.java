package com.kolev.radmil.controller;

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
import com.kolev.radmil.repository.GameRepository;
import com.kolev.radmil.repository.PlayerRepository;
import com.kolev.radmil.repository.SetRepository;
import com.kolev.radmil.service.GameService;

@Controller
public class GameController {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private SetRepository setRepository;

	@Autowired
	private GameService gameService;

	@RequestMapping(value = "/showAllGames")
	public String showAllGames(Model model) {
		List<Game> games = gameRepository.findAll();
		for (Game game : games) {
			game.setSets(setRepository.findBygameLike(game));
		}
		model.addAttribute("gamesList", games);
		return "showAllGames";
	}

	@RequestMapping(value = "/showPlayerGames", method = RequestMethod.GET)
	public ModelAndView showPlayerGamesForm() {
		return new ModelAndView("showPlayerGames", "player", new Player());
	}

	@RequestMapping(value = "/showPlayerGames", method = RequestMethod.POST)
	public String showPlayerGamesSubmit(@ModelAttribute Player player, Model model) {
		Player searchedPlayer = playerRepository.findByNameLike(player.getName());
		if (searchedPlayer == null) {
			model.addAttribute("player", player);
			return "pnf";
		}
		List<Game> games = gameRepository.findByPlayer1OrPlayer2(searchedPlayer, searchedPlayer);
		for (Game game : games) {
			game.setSets(setRepository.findBygameLike(game));
			if (game.getPlayer1().getCardId() != searchedPlayer.getCardId()) {
				game.setPlayer2(game.getPlayer1());
				game.setPlayer1(searchedPlayer);
				List<Set> sets = game.getSets();
				for (int i = 0; i < sets.size() / 2; i++) {
					sets.add(0, sets.get(sets.size() - 1));
					sets.remove(sets.size() - 1);
				}
			}
		}
		model.addAttribute("gamesList", games);
		return "showAllGames";
	}

	@RequestMapping(value = "/newGame", method = RequestMethod.GET)
	public ModelAndView gameForm() {
		return new ModelAndView("newGame", "game", new Game());
	}

	@RequestMapping(value = "/newGame", method = RequestMethod.POST)
	public String gameSubmit(@ModelAttribute Game game, Model model) {
		Player player1 = playerRepository.findOne(game.getPlayer1().getCardId());
		Player player2 = playerRepository.findOne(game.getPlayer2().getCardId());
		if (player1 == null) {
			model.addAttribute("player", player1);
			return "pnf";
		}
		if (player2 == null) {
			model.addAttribute("player", player2);
			return "pnf";
		}
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		gameService.addGame(game);
		model.addAttribute("game", game);
		return "newGameResult";
	}
}
