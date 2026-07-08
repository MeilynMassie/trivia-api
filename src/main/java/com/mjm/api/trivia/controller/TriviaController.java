package com.mjm.api.trivia.controller;

import com.mjm.api.trivia.model.TriviaQuestion;
import com.mjm.api.trivia.service.TriviaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("${app.api.base-path}/trivia")
public class TriviaController {

    private final TriviaService triviaService;

    public TriviaController(TriviaService triviaService) {
        this.triviaService = triviaService;
    }

    @GetMapping("/categories")
    public List<String> getAllCategories() {return triviaService.getAllCategories();}

    @PostMapping("/questions")
    public String getQuestions(
            @RequestParam(defaultValue = "5") int limit,
            @RequestParam(defaultValue = "video-games") String category, 
            Model model) {
        List<TriviaQuestion> questions = triviaService.getQuestionsByCategory(limit, category);
        model.addAttribute("questions", questions);
        return "start-game";
    }

    // @PostMapping("/submissions")
    // public ResponseEntity<AnswerResultDTO> submitAnswer(
    //         @Valid @RequestBody AnswerSubmissionDTO submission
    // ) {
    //     AnswerResultDTO result =
    //             triviaService.submitAnswer(submission);

    //     return ResponseEntity.ok(result);
    // }
}