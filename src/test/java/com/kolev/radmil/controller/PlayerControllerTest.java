package com.kolev.radmil.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;

import javax.persistence.PersistenceException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.kolev.radmil.entity.Player;
import com.kolev.radmil.repository.PlayerRepository;

@RunWith(MockitoJUnitRunner.class)
public class PlayerControllerTest {

	@Mock
	private PlayerRepository playerRepository;

	@InjectMocks
	private PlayerController playerController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(playerController).setViewResolvers(viewResolver()).build();
	}

	private InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Test
	public void testHomePage() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("home"));
	}

	@Test
	public void testGetNewPlayer() throws Exception {
		mockMvc.perform(get("/newPlayer"))
		.andExpect(status().isOk())
		.andExpect(view().name("newPlayer"))
		.andExpect(model().attributeExists("player"))
		.andExpect(model().size(1));

	}

	@Test
	public void testGetDelPlayer() throws Exception {
		mockMvc.perform(get("/delPlayer"))
		.andExpect(status().isOk())
		.andExpect(view().name("delPlayer"))
		.andExpect(model().attributeExists("player"))
		.andExpect(model().size(1));

	}

	@Test
	public void testGetAllPlayers() throws Exception {
		Player player1 = new Player();
		player1.setCardId(1);
		player1.setName("Rado");
		player1.setCountry("Bulgaria");
		player1.setRank(10);
		player1.setAge(24);

		Player player2 = new Player();
		player2.setCardId(2);
		player2.setName("Ivan");
		player2.setCountry("Spain");
		player2.setRank(5);
		player2.setAge(20);

		when(playerRepository.findAll()).thenReturn(Arrays.asList(player1, player2));

		mockMvc.perform(get("/showPlayers"))
		.andExpect(status().isOk())
		.andExpect(view().name("showPlayers"))
		.andExpect(model().attribute("playersList", hasSize(2)))
		.andExpect(model().attribute("playersList", hasItem(
				allOf(
						hasProperty("cardId", is(1)),
						hasProperty("name", is("Rado")),
						hasProperty("country", is("Bulgaria")),
						hasProperty("rank", is(10)),
						hasProperty("age", is(24))
						)
				)))
				.andExpect(model().attribute("playersList", hasItem(
						allOf(
								hasProperty("cardId", is(2)),
								hasProperty("name", is("Ivan")),
								hasProperty("country", is("Spain")),
								hasProperty("rank", is(5)),
								hasProperty("age", is(20))
								)
						)));
		verify(playerRepository, times(1)).findAll();
		verifyNoMoreInteractions(playerRepository);
	}

	@Test
	public void testPostDelPlayerSuccessful() throws Exception {
		Mockito.when(playerRepository.findByNameLike(anyString())).thenReturn(new Player());
		mockMvc.perform(post("/delPlayer").param("name", "Roger"))
		.andExpect(status().isOk())
		.andExpect(view().name("delResult"))
		.andExpect(model().attributeExists("player"))
		.andExpect(model().size(1));
	}

	@Test
	public void testPostDelPlayerNull() throws Exception {
		Mockito.when(playerRepository.findByNameLike(anyString())).thenReturn(null);
		mockMvc.perform(post("/delPlayer").param("name", "Roger"))
		.andExpect(status().isOk())
		.andExpect(view().name("pnf"))
		.andExpect(model().attributeExists("player"))
		.andExpect(model().size(1));
	}

	@Test
	public void testPostNewPlayerSuccessful() throws Exception {
		mockMvc.perform(post("/newPlayer"))
		.andExpect(status().isOk())
		.andExpect(view().name("newResult"))
		.andExpect(model().attributeExists("player"))
		.andExpect(model().size(1));
	}

	@Test
	public void testPostNewPlayerThrownException() throws Exception {
		Mockito.when(playerRepository.save(any(Player.class))).thenThrow(new JpaSystemException(new PersistenceException()));
		mockMvc.perform(post("/newPlayer"))
		.andExpect(status().isOk())
		.andExpect(view().name("newPlayer"))
		.andExpect(model().attributeExists("player"))
		.andExpect(model().size(1));
	}

}
