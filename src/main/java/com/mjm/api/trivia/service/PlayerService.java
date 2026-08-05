package com.mjm.api.trivia.service;

import java.util.List;
import java.util.Optional;

import com.mjm.api.trivia.model.Player;

public interface PlayerService {
    List<Player> getAllPlayers();
    Optional<Player> getPlayer(long id);
    int getScore(long id);
    void updateScore(long id);
    void addPlayer(Player player);
    void deletePlayer(long id);
}