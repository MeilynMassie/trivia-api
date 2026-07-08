package com.mjm.api.trivia.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Value;

import com.mjm.api.trivia.service.TriviaService;

@Controller
public class TriviaViewController {
    @Value("${app.api.base-path}/trivia")
    private String triviaBasePath;

    private final TriviaService triviaService;

    public TriviaViewController(TriviaService triviaService) {
        this.triviaService = triviaService;
    }
    @GetMapping("/mainMenu")
    public String showMainMenu(Model model) {
        List<String> categories = triviaService.getAllCategories();
        model.addAttribute("triviaBasePath", triviaBasePath);
        model.addAttribute("categories", categories);
        return "main-menu";
    }
}
