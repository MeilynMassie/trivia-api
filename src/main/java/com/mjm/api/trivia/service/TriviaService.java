package com.mjm.api.trivia.service;

import com.mjm.api.trivia.dto.AnswerResultDTO;
import com.mjm.api.trivia.dto.AnswerSubmissionDTO;
import com.mjm.api.trivia.dto.CategoryDTO;
import com.mjm.api.trivia.model.TriviaQuestion;
import com.mjm.api.trivia.repository.TriviaRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

public interface TriviaService {

        // Getters
        List<CategoryDTO> getAllCategories();
        List<TriviaQuestion> getRandomQuestions(int limit, String category);
        // DTOs
        AnswerResultDTO submitAnswer(AnswerSubmissionDTO submission);
        CategoryDTO mapToCategoryDTO(String category);
        // Formatters for Categories
        String formatLabel(String category);
        String testFormatLabel(String category);
}