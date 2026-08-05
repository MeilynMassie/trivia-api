package com.mjm.api.trivia.service;

import java.util.List;

// import org.springframework.data.domain.Page;

import com.mjm.api.trivia.model.TriviaQuestion;

public interface TriviaQuestionService {
    void loadQuestions();
    List<String> getCategories();
    // Page<TriviaQuestion> getQuestions(int page, int size, String category);
    List<TriviaQuestion> getQuestions(String category, int limit);
}