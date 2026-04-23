package com.mjm.api.trivia.controller;

import com.mjm.api.trivia.dto.AnswerResultDTO;
import com.mjm.api.trivia.dto.AnswerSubmissionDTO;
import com.mjm.api.trivia.dto.CategoryDTO;
import com.mjm.api.trivia.model.TriviaQuestion;
import com.mjm.api.trivia.service.TriviaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/trivia")
public class TriviaController {

    private final TriviaService service;

    public TriviaController(TriviaService service) {
        this.service = service;
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories() {return service.getAllCategories();}

    @GetMapping("/questions")
    public List<TriviaQuestion> getQuestions(
            @RequestParam(defaultValue = "5") int limit,
            @RequestParam(defaultValue = "random") String category) {
        return service.getRandomQuestions(limit, category);
    }

    @PostMapping("/submissions")
    public ResponseEntity<AnswerResultDTO> submitAnswer(
            @Valid @RequestBody AnswerSubmissionDTO submission
    ) {
        AnswerResultDTO result =
                service.submitAnswer(submission);

        return ResponseEntity.ok(result);
    }
}