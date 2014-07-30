package com.kolev.radmil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kolev.radmil.entity.Game;
import com.kolev.radmil.entity.Player;

public interface GameRepository extends JpaRepository<Game, Integer> {

	public List<Game> findByPlayer1OrPlayer2(Player player1, Player player2);

}
