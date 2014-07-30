package com.kolev.radmil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kolev.radmil.entity.Game;
import com.kolev.radmil.entity.Set;

public interface SetRepository extends JpaRepository<Set, Integer> {

	public List<Set> findBygameLike(Game game);
}
