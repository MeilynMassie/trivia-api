package com.mjm.api.trivia.controller;

import com.mjm.api.trivia.model.TriviaQuestion;
import com.mjm.api.trivia.service.TriviaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trivia")
public class TriviaController {

    private final TriviaService service;

    public TriviaController(TriviaService service) {
        this.service = service;
    }

    @GetMapping
    public List<TriviaQuestion> getAllQuestions() {
        return service.getAllQuestions();
    }
}