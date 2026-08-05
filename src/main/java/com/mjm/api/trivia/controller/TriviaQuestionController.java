package com.mjm.api.trivia.controller;

import com.mjm.api.trivia.model.TriviaQuestion;
import com.mjm.api.trivia.service.TriviaQuestionService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RequestMapping("${app.api.base-path}/trivia-question")
@RestController
public class TriviaQuestionController {
    private TriviaQuestionService triviaQuestionService;

    public TriviaQuestionController (TriviaQuestionService triviaQuestionService) {
        this.triviaQuestionService = triviaQuestionService;
    }

    @GetMapping("/category")
    public List<String> getAllCategories() {
        return triviaQuestionService.getCategories();
    }
    
    // @GetMapping
    // public Page<TriviaQuestion> getQuestions(
    //         @RequestParam(defaultValue = "general") String category,
    //         @RequestParam(defaultValue = "0") int page,
    //         @RequestParam(defaultValue = "10") int size) {

    //     return triviaQuestionService.getQuestions(page, size, category);
    // }
    @GetMapping
    public List<TriviaQuestion> getQuestions(
            @RequestParam(defaultValue = "general") String category,
            @RequestParam(defaultValue = "10") int limit) {

        return triviaQuestionService.getQuestions(category, limit);
    }
}
