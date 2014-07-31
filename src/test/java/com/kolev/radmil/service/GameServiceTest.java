package com.kolev.radmil.service;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.kolev.radmil.entity.Game;
import com.kolev.radmil.entity.Player;
import com.kolev.radmil.entity.Set;
import com.kolev.radmil.repository.GameRepository;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

	@Mock
	private GameRepository gameRepository;

	@InjectMocks
	private GameService gameService;

	@Before
	public void setup() {
		Mockito.when(gameRepository.save(any(Game.class))).thenAnswer(new Answer<Game>() {

			@Override
			public Game answer(InvocationOnMock invocation) throws Throwable {
				Game game = (Game) invocation.getArguments()[0];
				return game;
			}
		});
	}

	@Test()
	public void testAddGameMethod() {
		Player player1 = new Player();
		player1.setName("Radmil");
		Player player2 = new Player();
		player2.setName("Simeon");
		Date date = new Date();
		List<Set> sets = new ArrayList<Set>();
		Set set1 = new Set();
		set1.setGamesWon(6);
		Set set2 = new Set();
		set2.setGamesWon(3);
		Set set3 = new Set();
		Set set4 = new Set();
		set4.setGamesWon(2);
		Set set5 = new Set();
		set5.setGamesWon(6);
		Set set6 = new Set();
		sets.add(set1);
		sets.add(set2);
		sets.add(set3);
		sets.add(set4);
		sets.add(set5);
		sets.add(set6);
		
		Game inputGame = new Game();
		inputGame.setDate(date);
		inputGame.setPlayer1(player1);
		inputGame.setPlayer2(player2);
		inputGame.setSets(sets);
		
		Game outputGame1 = gameService.addGame(inputGame);
		assertEquals(outputGame1.getSets().size(), 4);
		assertThat(outputGame1.getSets().get(0).getGamesWon(), is(6));
		assertThat(outputGame1.getSets().get(1).getGamesWon(), is(3));
		assertThat(outputGame1.getSets().get(2).getGamesWon(), is(2));
		assertThat(outputGame1.getSets().get(3).getGamesWon(), is(6));
		
		set3.setGamesWon(6);
		inputGame.getSets().add(2, set3);
		set6.setGamesWon(1);
		inputGame.getSets().add(set6);
		
		Game outputGame2 = gameService.addGame(inputGame);
		assertEquals(outputGame2.getSets().size(), 6);

		assertThat(outputGame2.getSets().get(0).getGamesWon(), is(6));
		assertThat(outputGame2.getSets().get(1).getGamesWon(), is(3));
		assertThat(outputGame2.getSets().get(2).getGamesWon(), is(6));
		assertThat(outputGame2.getSets().get(3).getGamesWon(), is(2));
		assertThat(outputGame2.getSets().get(4).getGamesWon(), is(6));
		assertThat(outputGame2.getSets().get(5).getGamesWon(), is(1));
	}

}
