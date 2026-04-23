package com.mjm.api.trivia.service;

import com.mjm.api.trivia.dto.AnswerResultDTO;
import com.mjm.api.trivia.dto.AnswerSubmissionDTO;
import com.mjm.api.trivia.dto.CategoryDTO;
import com.mjm.api.trivia.model.TriviaQuestion;
import com.mjm.api.trivia.repository.TriviaRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TriviaService {
    public static void main(String[] args) {
        System.out.println(testFormatLabel("science_technology"));
    }

    private final TriviaRepository repository;

    public TriviaService(TriviaRepository repository) {
        this.repository = repository;
    }

    /* Get Categories */
    public List<CategoryDTO> getAllCategories() {
        List<String> categories = repository.findAllCategories();

        return categories.stream()
                .map(this::mapToCategoryDTO)
                .toList();
    }

    public List<TriviaQuestion> getRandomQuestions(int limit, String category) {
        return repository.findRandomQuestions(limit, category);
    }

    public AnswerResultDTO submitAnswer(
            AnswerSubmissionDTO submission
    ) {

        TriviaQuestion question =
                repository.findById(
                        submission.questionId()
                ).orElseThrow(
                        () -> new RuntimeException(
                                "Question not found"
                        )
                );

        boolean correct =
                question.getAnswer()
                        .equalsIgnoreCase(
                                submission.selectedAnswer()
                        );

        return new AnswerResultDTO(
                correct,
                question.getAnswer()
        );
    }
    private CategoryDTO mapToCategoryDTO(String category) {
        return new CategoryDTO(
                category,
                formatLabel(category)
        );
    }

    private String formatLabel(String category) {
        String[] words = category.split("_");
        System.out.println(Arrays.stream(words).toList());

        return Arrays.stream(words)
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .reduce((a, b) -> a + " " + b)
                .orElse(category);
    }

    private static String testFormatLabel(String category) {
        String[] words = category.split("_");

        String word = "Test";
        return word;
    }
}