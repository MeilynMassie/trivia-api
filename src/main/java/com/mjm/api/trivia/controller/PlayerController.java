package com.mjm.api.trivia.controller;

import com.mjm.api.trivia.dto.CreatePlayerDTO;
import com.mjm.api.trivia.model.Player;
import com.mjm.api.trivia.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("${app.api.base-path}/player")
@RestController
public class PlayerController {
    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    // @GetMapping("/{id}")
    // public Player getPlayer(@PathVariable Long id) {
    // return playerService.getPlayer(id);
    // }

    // @PostMapping
    // public void createPlayer(
    // @Valid @RequestBody CreatePlayerDTO dto
    // ) {
    // playerService.createPlayer(dto.name());
    // }
}
