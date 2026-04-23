package com.mjm.api.trivia.service;

import com.mjm.api.trivia.model.Player;
import com.mjm.api.trivia.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player createPlayer(String name) {
        Player player = new Player();
        player.setName(name);
        player.setScore(0);
        return playerRepository.save(player);
    }

    public Player updateScore(Long playerId, boolean correct) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        if (correct) {
            player.setScore(player.getScore() + 1);
        }

        return playerRepository.save(player);
    }

    public Player getPlayer(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    public List<Player> getLeaderboard() {
        return playerRepository.findAll()
                .stream()
                .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
                .toList();
    }
}