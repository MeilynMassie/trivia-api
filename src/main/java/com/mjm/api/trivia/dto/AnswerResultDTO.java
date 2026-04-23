package com.mjm.api.trivia.dto;

public record AnswerResultDTO(
        boolean correct,
        String correctAnswer
        ) {
}
