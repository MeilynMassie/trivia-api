package com.mjm.api.trivia.controller;

import com.mjm.api.trivia.service.DataLoaderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class DataLoaderController {

    private final DataLoaderService service;

    public DataLoaderController(DataLoaderService service) {
        this.service = service;
    }

    @PostMapping("/load-data")
    public String loadData() throws Exception {
        service.loadData();
        return "Data loaded!";
    }
}