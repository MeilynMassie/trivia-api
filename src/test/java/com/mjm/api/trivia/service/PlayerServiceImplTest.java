// package com.mjm.api.trivia.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.mjm.api.trivia.model.Player;
// import com.mjm.api.trivia.repository.PlayerRepository;
// import com.mjm.api.trivia.service.impl.PlayerServiceImpl;

// @ExtendWith(MockitoExtension.class)
// public class PlayerServiceImplTest {

//     @Mock
//     private PlayerRepository playerRepository;

//     @InjectMocks
//     private PlayerServiceImpl playerService;

//     @Test
//     void shouldReturnAllPlayers() {
//         Player player1 = new Player();
//         player1.setId(1L);
//         player1.setName("Dummy 1");

//         Player player2 = new Player();
//         player2.setId(2L);
//         player2.setName("Dummy 2");

//         List<Player> expected = List.of(player1, player2);

//         when(playerRepository.findAll())
//             .thenReturn(expected);

//         List<Player> actual = playerService.getAllPlayers();

//         assertEquals(expected, actual);

//         verify(playerRepository).findAll();
//     }

//     @Test
//     void shouldReturnAPlayer() {
//         Player expected = new Player();
//         expected.setId(1L);
//         expected.setName("Dummy");

//         when(playerRepository.findById(expected.getId()))
//             .thenReturn(Optional.of(expected));
        
//         Optional<Player> actual = playerService.getPlayer(expected.getId());

//         assertEquals(Optional.of(expected), actual);

//         verify(playerRepository).findById(expected.getId());
//     }

//     @Test
//     void shouldThrowPlayerNotFound() {
//         when(playerRepository.findById(1L))
//             .thenReturn(Optional.empty());

//         Optional<Player> actual = playerService.getPlayer(1L);

//         assertEquals(Optional.empty(), actual);
//     }

//     @Test
//     void shouldThrowExceptionWhenPlayerNotFoundWithGetScore() {
//         when(playerRepository.findById(1L))
//             .thenReturn(Optional.empty());

//         RuntimeException exception = assertThrows(
//             RuntimeException.class,
//             () -> playerService.getScore(1L)
//         );

//         assertEquals("Player not found", exception.getMessage());
//     }

//     @Test
//     void shouldThrowExceptionWhenPlayerNotFoundWithUpdateScore() {
//         when(playerRepository.findById(1L))
//             .thenReturn(Optional.empty());

//         RuntimeException exception = assertThrows(
//             RuntimeException.class,
//             () -> playerService.updateScore(1L)
//         );

//         assertEquals("Player not found", exception.getMessage());
//     }

//     @Test
//     void shouldThrowExceptionWhenPlayerNotFoundWithDelete() {
//         when(playerRepository.findById(1L))
//             .thenReturn(Optional.empty());

//         RuntimeException exception = assertThrows(
//             RuntimeException.class,
//             () -> playerService.deletePlayer(1L)
//         );

//         assertEquals("Player not found", exception.getMessage());
//     }

//     @Test
//     void shouldReturnScore() {
//         Player player = new Player();
//         player.setId(1L);
//         player.setScore(4);

//         when(playerRepository.findById(player.getId()))
//             .thenReturn(Optional.of(player));

//         int expected = player.getScore();
//         int actual = playerService.getScore(player.getId());

//         assertEquals(expected, actual);
//         verify(playerRepository).findById(player.getId());
//     }

//     @Test
//     void shouldUpdateScore() {
//         Player player = new Player();
//         player.setId(1L);
//         player.setScore(4);

//         when(playerRepository.findById(player.getId()))
//             .thenReturn(Optional.of(player));

//         int expected = player.getScore() + 1;

//         playerService.updateScore(player.getId());

//         int actual = player.getScore();

//         assertEquals(expected, actual);
//         verify(playerRepository).findById(player.getId());
//     }

//     @Test
//     void shouldAddPlayer() {
//         Player player = new Player();
//         player.setId(1L);
//         player.setName("Dummy");
//         playerService.addPlayer(player);
//         verify(playerRepository).save(player);
//     }

//     @Test
//     void shouldDeletePlayer() {
//         Player player = new Player();
//         player.setId(1L);
//         player.setName("Dummy");

//         when(playerRepository.findById(player.getId()))
//             .thenReturn(Optional.of(player));

//         playerService.deletePlayer(player.getId());

//         verify(playerRepository).delete(player);
//     }
// }
