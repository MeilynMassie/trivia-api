package com.mjm.api.trivia.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjm.api.trivia.model.TriviaQuestion;
import com.mjm.api.trivia.repository.TriviaRepository;
import com.mjm.api.trivia.service.DataLoaderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataLoaderServiceImpl implements DataLoaderService{

    private TriviaRepository repository;

    @Autowired
    public DataLoaderServiceImpl(TriviaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        PathMatchingResourcePatternResolver resolver =
                new PathMatchingResourcePatternResolver();

        Resource[] resources = resolver.getResources("classpath:/data/*.json");
        // Resource[] resources = {
        //         resolver.getResource("classpath:/data/animals.json"),
        //         resolver.getResource("classpath:/data/general.json"),
        //         resolver.getResource("classpath:/data/video-games.json")
        // };

        for (Resource resource : resources) {
            System.out.println("Loading: " + resource.getFilename());

            List<TriviaQuestion> questions =
                    mapper.readValue(resource.getInputStream(), new TypeReference<>() {});

            repository.saveAll(questions);
        }

        System.out.println("All data loaded!");
    }
}