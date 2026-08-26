package com.mjm.api.trivia.controller;

import com.mjm.api.trivia.model.Admin;
import com.mjm.api.trivia.service.AdminService;
import com.mjm.api.trivia.service.PlayerService;
import com.mjm.api.trivia.service.TriviaQuestionService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${app.api.base-path}/admin")
public class AdminController {

    private final TriviaQuestionService triviaQuestionService;
    private final PlayerService playerService;
    private final AdminService adminService;

    public AdminController(
            TriviaQuestionService triviaQuestionService,
            PlayerService playerService,
            AdminService adminService
        ) {

        this.triviaQuestionService = triviaQuestionService;
        this.playerService = playerService;
        this.adminService = adminService;
    }

    @PostMapping("/load-data")
    public void loadData(@RequestParam(defaultValue = "-1") int limit) {
        triviaQuestionService.loadQuestions(limit);
    }

    @GetMapping("/{id}")
    public String getAdmin(@PathVariable Long id) {
        return adminService.getAdmin(id);
    }

    @PostMapping
    public void addAdmin(@Valid @RequestBody Admin admin) {
        adminService.addAdmin(admin);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }

    @DeleteMapping("/player/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }
}