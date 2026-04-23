package com.mjm.api.trivia.controller;

import com.mjm.api.trivia.dto.CreatePlayerDTO;
import com.mjm.api.trivia.model.Player;
import com.mjm.api.trivia.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/trivia/players")
@RestController
public class PlayerController {
    private final PlayerService playerService;
    public PlayerController(PlayerService playerService) { this.playerService = playerService; }

    @PostMapping("/create")
    public Player createPlayer(
            @Valid @RequestBody CreatePlayerDTO dto
    ) {
        return playerService.createPlayer(dto.name());
    }

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable Long id) {
        return playerService.getPlayer(id);
    }

    @GetMapping("/leaderboard")
    public List<Player> leaderboard() {
        return playerService.getLeaderboard();
    }
}
