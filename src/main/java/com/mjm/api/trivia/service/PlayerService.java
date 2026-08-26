package com.mjm.api.trivia.service;

import java.util.List;

import com.mjm.api.trivia.model.Player;

public interface PlayerService {
    List<Player> getAllPlayers();
    Player getPlayer(long id);
    int getScore(long id);
    void updateScore(long id);
    void addPlayer(Player player);
    void deletePlayer(long id);
}