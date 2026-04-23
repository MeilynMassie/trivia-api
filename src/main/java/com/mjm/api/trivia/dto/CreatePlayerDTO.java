package com.mjm.api.trivia.dto;

import jakarta.validation.constraints.NotNull;

public record CreatePlayerDTO(
        @NotNull (message = "Name is required")
        String name
) {}