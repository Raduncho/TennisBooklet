package com.kolev.radmil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kolev.radmil.entity.Player;
import com.kolev.radmil.service.PlayerService;

@Controller
public class PlayerController {

	@Autowired
	private PlayerService service;

	@RequestMapping(value = "/")
	public ModelAndView home() {
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/newPlayer", method = RequestMethod.GET)
	public ModelAndView playerForm() {
		return new ModelAndView("newPlayer", "command", new Player());
	}

	@RequestMapping(value = "/newPlayer", method = RequestMethod.POST)
	public String playerSubmit(@ModelAttribute Player player, Model model) {
		service.addPlayer(player);
		model.addAttribute("player", player);
		return "newResult";
	}
	
	@RequestMapping(value = "/delPlayer", method = RequestMethod.GET)
	public ModelAndView delPlayerForm() {
		return new ModelAndView("delPlayer", "command", new Player());
	}

	@RequestMapping(value = "/delPlayer", method = RequestMethod.POST)
	public String delPlayerSubmit(@ModelAttribute Player player, Model model) {
		Player searchedPlayer = service.getPlayer(player.getCardId());
		service.removePlayer(player.getCardId());
		model.addAttribute("player", searchedPlayer);
		return "delResult";
	}

	@RequestMapping(value = "/showPlayers")
	public String showPlayers(Model model) {
		List<Player> allPlayers= service.getAllPlayers();
		model.addAttribute("playersList", allPlayers);
		return "showPlayers";
	}
}
