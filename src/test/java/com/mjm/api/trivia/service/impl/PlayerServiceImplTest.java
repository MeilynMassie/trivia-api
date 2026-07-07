package com.mjm.api.trivia.service.impl;

import com.mjm.api.trivia.exception.PlayerNotFoundException;
import com.mjm.api.trivia.model.Player;
import com.mjm.api.trivia.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    void getPlayerShouldThrowPlayerNotFoundExceptionWhenMissing() {
        when(playerRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PlayerNotFoundException.class, () -> playerService.getPlayer(99L));
    }
}
