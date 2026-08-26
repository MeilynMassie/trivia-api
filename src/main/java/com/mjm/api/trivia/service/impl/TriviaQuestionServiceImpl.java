package com.mjm.api.trivia.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.mjm.api.trivia.dto.TriviaQuestionDTO;
import com.mjm.api.trivia.model.TriviaChoice;
import com.mjm.api.trivia.model.TriviaQuestion;
import com.mjm.api.trivia.repository.TriviaQuestionRepository;
import com.mjm.api.trivia.service.TriviaQuestionService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


@Service
public class TriviaQuestionServiceImpl implements TriviaQuestionService{
    private TriviaQuestionRepository triviaQuestionRepository;
    private ObjectMapper objectMapper;
    private final ResourcePatternResolver resolver;
                

    public TriviaQuestionServiceImpl(TriviaQuestionRepository triviaQuestionRepository, ObjectMapper objectMapper, ResourcePatternResolver resolver) { 
        this.objectMapper = objectMapper;
        this.resolver = resolver;
        this.triviaQuestionRepository = triviaQuestionRepository; 
    }


    @Override
    public List<String> getCategories() {
        return triviaQuestionRepository.findAllCategories();
    }

    @Transactional
    @Override
    public void loadQuestions(int limit) {
        try {
            Resource[] resources = resolver.getResources("classpath:/data/*.json");

            for (Resource resource : resources) {
                int counter = 0;
                System.out.println("Loading: " + resource.getFilename());

                List<TriviaQuestionDTO> questions =
                        objectMapper.readValue(
                                resource.getInputStream(),
                                new TypeReference<List<TriviaQuestionDTO>>() {});

                for (TriviaQuestionDTO dto : questions) {

                    if (limit != -1 && counter >= limit) {
                        break;
                    }

                    TriviaQuestion question = new TriviaQuestion();
                    question.setQuestion(dto.getQuestion());
                    question.setCategory(dto.getCategory());

                    List<TriviaChoice> choices = new ArrayList<>();

                    short order = 1;

                    for (String choiceText : dto.getChoices()) {
                        TriviaChoice choice = new TriviaChoice();
                        choice.setQuestion(question);
                        choice.setChoiceText(choiceText);
                        choice.setDisplayOrder(order++);
                        choice.setIsCorrect(choiceText.equals(dto.getAnswer()));

                        choices.add(choice);
                    }

                    question.setChoices(choices);
                    triviaQuestionRepository.save(question);

                    counter++;
                }
            }

            System.out.println("All resources loaded");

        } catch (IOException e) {
            throw new RuntimeException("Failed to load questions", e);
        }
    }


    // @Override
    // public List<TriviaQuestion> getQuestions(int limit, String category) {

    //     return triviaQuestionRepository.findByCategory(category, pageable);
    // }
    @Override
    public List<TriviaQuestion> getQuestions(String category, int limit) {
        return triviaQuestionRepository.findByCategory(category, limit);
    }


    @Override
    public Boolean checkAnswer(Long questionId, Long choiceId) {
        TriviaQuestion question = triviaQuestionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        return question.getChoices()
                .stream()
                .filter(c -> c.getId().equals(choiceId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Choice not found"))
                .getIsCorrect();
    }
}
