package com.mjm.api.trivia.service;

import com.mjm.api.trivia.model.TriviaQuestion;
import com.mjm.api.trivia.repository.TriviaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriviaService {

    private final TriviaRepository repository;

    public TriviaService(TriviaRepository repository) {
        this.repository = repository;
    }

    public List<TriviaQuestion> getAllQuestions() {
        return repository.findAll();
    }
}