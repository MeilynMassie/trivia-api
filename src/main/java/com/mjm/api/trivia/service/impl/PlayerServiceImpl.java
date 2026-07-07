package com.mjm.api.trivia.service.impl;

import com.mjm.api.trivia.exception.PlayerNotFoundException;
import com.mjm.api.trivia.model.Player;
import com.mjm.api.trivia.repository.PlayerRepository;
import com.mjm.api.trivia.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player createPlayer(String name) {
        Player player = new Player();
        player.setName(name);
        player.setScore(0);
        return playerRepository.save(player);
    }

    @Override
    public Player updateScore(Long playerId, boolean correct) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));

        if (correct) {
            player.setScore(player.getScore() + 1);
        }

        return playerRepository.save(player);
    }

    @Override
    public Player getPlayer(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();  
    }
}