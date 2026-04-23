package com.mjm.api.trivia.dto;

import jakarta.validation.constraints.NotNull;

public record AnswerSubmissionDTO(
        @NotNull(message = "Question Id required")
        Long questionId,
        @NotNull(message = "Selected Answer required")
        String selectedAnswer
    ) {
}

