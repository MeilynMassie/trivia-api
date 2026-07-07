package com.mjm.api.trivia.service;

import com.mjm.api.trivia.model.Player;
import com.mjm.api.trivia.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

public interface PlayerService {
    Player createPlayer(String name);
    Player getPlayer(Long id);
    List<Player> getAllPlayers();
    // Score
    Player updateScore(Long playerId, boolean correct);
}