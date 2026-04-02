package com.mjm.api.trivia.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjm.api.trivia.model.TriviaQuestion;
import com.mjm.api.trivia.repository.TriviaRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataLoaderService {

    private final TriviaRepository repository;

    public DataLoaderService(TriviaRepository repository) {
        this.repository = repository;
    }

    public void loadData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        PathMatchingResourcePatternResolver resolver =
                new PathMatchingResourcePatternResolver();

        Resource[] resources = resolver.getResources("classpath:/data/*.json");

        for (Resource resource : resources) {
            System.out.println("Loading: " + resource.getFilename());

            List<TriviaQuestion> questions =
                    mapper.readValue(resource.getInputStream(), new TypeReference<>() {});

            repository.saveAll(questions);
        }

        System.out.println("All data loaded!");
    }
}