package com.kolev.radmil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kolev.radmil.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

	public Player findByNameLike(String name);
}
