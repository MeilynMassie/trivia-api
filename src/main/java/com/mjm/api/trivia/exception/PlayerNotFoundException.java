package com.mjm.api.trivia.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(Long playerId) {
        super("Player not found with id: " + playerId);
    }
}
