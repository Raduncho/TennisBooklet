//package com.kolev.radmil.repository;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.core.IsNull.notNullValue;
//import static org.hamcrest.core.IsNull.nullValue;
//import static org.junit.Assert.assertThat;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.kolev.radmil.config.BookletConfiguration;
//import com.kolev.radmil.entity.Player;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { BookletConfiguration.class })
//@Transactional
//public class PlayerRepositoryTest {
//
//	@Autowired
//	private PlayerRepository repository;
//
//	@Test
//	public void testSavingPlayer() {
//		Player player = new Player();
//		player.setName("Radmil");
//		assertThat(player.getCardId(), is(nullValue()));
//		repository.save(player);
//		assertThat(player.getCardId(), is(notNullValue()));
//		Player searchedPlayer = repository.findByNameLike("Radmil");
//		assertThat(searchedPlayer, is(notNullValue()));
//		assertThat(searchedPlayer.getName(), is("Radmil"));
//		assertThat(searchedPlayer.getCardId(), is(player.getCardId()));
//	}
//
// }
