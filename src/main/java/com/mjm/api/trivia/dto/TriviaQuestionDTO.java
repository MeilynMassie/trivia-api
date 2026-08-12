package com.mjm.api.trivia.dto;

import lombok.Data;

import java.util.List;

@Data
public class TriviaQuestionDTO {
    private String question;
    private String category;
    private String answer;
    private List<String> choices;
}