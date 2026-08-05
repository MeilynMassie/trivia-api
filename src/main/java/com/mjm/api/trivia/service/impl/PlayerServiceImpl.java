package com.mjm.api.trivia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mjm.api.trivia.model.Player;
import com.mjm.api.trivia.repository.PlayerRepository;
import com.mjm.api.trivia.service.PlayerService;

import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerServiceImpl implements PlayerService{
    private PlayerRepository playerRepository;
    public PlayerServiceImpl(PlayerRepository playerRepository) {this.playerRepository = playerRepository;}


    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }


    @Override
    public Optional<Player> getPlayer(long id) {
        return playerRepository.findById(id);
    }


    @Override
    public int getScore(long id) {
        return playerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Player not found"))
            .getScore();
    }


    @Transactional
    @Override
    public void updateScore(long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        player.setScore(player.getScore() + 1);
    }


    @Override
    public void addPlayer(Player player) {
        playerRepository.save(player);
    }

    
    @Transactional
    @Override
    public void deletePlayer(long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
                
        playerRepository.delete(player);
    }
}
