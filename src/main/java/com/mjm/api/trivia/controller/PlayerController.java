package com.mjm.api.trivia.controller;

import java.util.List;
import java.util.Optional;

import com.mjm.api.trivia.model.Player;
import com.mjm.api.trivia.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("${app.api.base-path}/player")
@RestController
public class PlayerController {
    private PlayerService playerService;
    public PlayerController(PlayerService playerService) {this.playerService = playerService;}

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public Optional<Player> getPlayer(@PathVariable Long id) {
        return playerService.getPlayer(id);
    }

    @GetMapping("/{id}/score")
    public int getScore(@PathVariable Long id) {
        return playerService.getScore(id);
    }

    @PostMapping
    public void addPlayer(@RequestBody Player player) {
        playerService.addPlayer(player);
    }

    @PutMapping("/{id}/score")
    public void updateScore(@PathVariable Long id) {
        playerService.updateScore(id);
    }
    
}