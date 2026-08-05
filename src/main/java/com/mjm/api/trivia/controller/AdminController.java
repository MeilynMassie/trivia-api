package com.mjm.api.trivia.controller;

import com.mjm.api.trivia.model.TriviaQuestion;
import com.mjm.api.trivia.service.PlayerService;
import com.mjm.api.trivia.service.TriviaQuestionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("${app.api.base-path}/admin")
public class AdminController {

    private final TriviaQuestionService triviaQuestionService;
    private final PlayerService playerService;

    public AdminController(
            TriviaQuestionService triviaQuestionService,
            PlayerService playerService) {

        this.triviaQuestionService = triviaQuestionService;
        this.playerService = playerService;
    }

    @PostMapping("/load-data")
    public void loadData() {
        triviaQuestionService.loadQuestions();
    }

    @DeleteMapping("/player/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }
}