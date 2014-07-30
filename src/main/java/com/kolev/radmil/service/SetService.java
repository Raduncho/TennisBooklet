package com.kolev.radmil.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kolev.radmil.entity.Game;
import com.kolev.radmil.entity.Set;
import com.kolev.radmil.repository.SetRepository;

@Service
public class SetService {

	@Autowired
	private SetRepository repository;

	@Transactional
	public void addSet(Set set) {
		repository.save(set);
	}

	public Set getSet(int setId) {
		return repository.findOne(setId);
	}

	public List<Set> getSetsByGame(Game game) {
		return repository.findBygameLike(game);
	}

}
