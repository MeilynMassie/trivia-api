package com.mjm.api.trivia.service;

import com.mjm.api.trivia.dto.AnswerResultDTO;
import com.mjm.api.trivia.dto.AnswerSubmissionDTO;
import com.mjm.api.trivia.dto.CategoryDTO;
import com.mjm.api.trivia.model.TriviaQuestion;

import java.util.List;

public interface TriviaService {

        // Getters
        List<String> getAllCategories();
        List<TriviaQuestion> getQuestionsByCategory(int limit, String category);
        // // DTOs
        // AnswerResultDTO submitAnswer(AnswerSubmissionDTO submission);
}