package com.mjm.api.trivia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mjm.api.trivia.model.Player;
import com.mjm.api.trivia.service.PlayerService;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController controller;

    @Test
    void shouldGetAllPlayers() {
        Player p1 = new Player();
        p1.setId(1L);
        p1.setName("Melon Lord");

        Player p2 = new Player();
        p2.setId(2L);
        p2.setName("Dummy");

        List<Player> players = List.of(p1, p2);
        when(playerService.getAllPlayers()).thenReturn(players);

        List<Player> result = controller.getAllPlayers();

        assertEquals(players, result);
        verify(playerService).getAllPlayers();
    }

    @Test
    void shouldGetPlayerById() {
        Player player = new Player();
        player.setId(3L);
        player.setName("Twinkle Toes");

        when(playerService.getPlayer(3L)).thenReturn(player);

        Player result = controller.getPlayer(3L);

        assertEquals(player, result);
        verify(playerService).getPlayer(3L);
    }

    @Test
    void shouldGetPlayerScore() {
        when(playerService.getScore(4L)).thenReturn(8);

        int result = controller.getScore(4L);

        assertEquals(8, result);
        verify(playerService).getScore(4L);
    }

    @Test
    void shouldAddPlayer() {
        Player player = new Player();
        player.setId(5L);
        player.setName("Sparky Sparky Boom Man");

        controller.addPlayer(player);

        verify(playerService).addPlayer(player);
    }

    @Test
    void shouldUpdateScore() {
        controller.updateScore(9L);

        verify(playerService).updateScore(9L);
    }
}
