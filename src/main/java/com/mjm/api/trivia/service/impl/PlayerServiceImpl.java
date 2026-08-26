package com.mjm.api.trivia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mjm.api.trivia.exception.ResourceNotFoundException;
import com.mjm.api.trivia.model.Player;
import com.mjm.api.trivia.repository.PlayerRepository;
import com.mjm.api.trivia.service.PlayerService;

import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerServiceImpl implements PlayerService{
    private PlayerRepository playerRepository;
    private final String className = Player.class.getSimpleName();

    public PlayerServiceImpl(PlayerRepository playerRepository) {this.playerRepository = playerRepository;}


    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }


    @Override
    public Player getPlayer(long id) {
        return playerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(className, id));
    }


    @Override
    public int getScore(long id) {
        return playerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(className, id))
            .getScore();
    }


    @Transactional
    @Override
    public void updateScore(long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(className, id));
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
                .orElseThrow(() -> new ResourceNotFoundException(className, id));
        playerRepository.delete(player);
    }
}
