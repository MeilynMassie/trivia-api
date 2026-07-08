package com.mjm.api.trivia.service.impl;

import com.mjm.api.trivia.dto.AnswerResultDTO;
import com.mjm.api.trivia.dto.AnswerSubmissionDTO;
import com.mjm.api.trivia.dto.CategoryDTO;
import com.mjm.api.trivia.model.TriviaQuestion;
import com.mjm.api.trivia.repository.TriviaRepository;
import com.mjm.api.trivia.service.TriviaService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TriviaServiceImpl implements TriviaService {

        private TriviaRepository repository;

        @Autowired
        public TriviaServiceImpl(TriviaRepository repository) { this.repository = repository; }

        @Override
        public List<String> getAllCategories() {
                List<String> categories = repository.findAllCategories();
                return categories;
        }
        

        @Override
        public List<TriviaQuestion> getQuestionsByCategory(int limit, String category) {
                return repository.getQuestionsByCategory(limit, category);
        }

        // @Override
        // public AnswerResultDTO submitAnswer(
        //         AnswerSubmissionDTO submission
        // ) {

        //         TriviaQuestion question =
        //                 repository.findById(
        //                         submission.questionId()
        //                 ).orElseThrow(
        //                         () -> new RuntimeException(
        //                                 "Question not found"
        //                         )
        //                 );

        //         boolean correct =
        //                 question.getAnswer()
        //                         .equalsIgnoreCase(
        //                                 submission.selectedAnswer()
        //                         );

        //         return new AnswerResultDTO(
        //                 correct,
        //                 question.getAnswer()
        //         );
        // }
}