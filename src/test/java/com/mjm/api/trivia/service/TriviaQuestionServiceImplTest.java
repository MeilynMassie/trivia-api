package com.mjm.api.trivia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mjm.api.trivia.model.TriviaChoice;
import com.mjm.api.trivia.model.TriviaQuestion;
import com.mjm.api.trivia.repository.TriviaQuestionRepository;
import com.mjm.api.trivia.service.impl.TriviaQuestionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TriviaQuestionServiceImplTest {
    @Mock
    private TriviaQuestionRepository triviaQuestionRepository;

    @InjectMocks
    private TriviaQuestionServiceImpl service;

    @Test
    void returnAllCategories() {
        List<String> expected = List.of(
                                    "celebrities", "world", "history", "rated", "brain-teasers",
                                    "for-kids", "video-games", "literature", "television", "animals",
                                    "people", "science-technology", "sports", "music", "entertainment", 
                                    "newest", "religion-faith", "geography", "movies", "hobbies",
                                    "humanities", "general");

        when(triviaQuestionRepository.findAllCategories())
                .thenReturn(expected);

        List<String> result = service.getCategories();

        assertEquals(expected, result);

        verify(triviaQuestionRepository).findAllCategories();
        }

    @Test
    void returnTwoQuestionsForVideoGames() {
        TriviaQuestion q1 = new TriviaQuestion();
        q1.setQuestion("Q1?");
        q1.setCategory("video-games");
        TriviaQuestion q2 = new TriviaQuestion();
        q2.setQuestion("Q2?");
        q2.setCategory("video-games");
        List<TriviaQuestion> expected = List.of(q1, q2);
        when(triviaQuestionRepository.findByCategory("video-games", 5))
                .thenReturn(expected);
        List<TriviaQuestion> result = service.getQuestions("video-games", 5);
        assertEquals(expected, result);
        verify(triviaQuestionRepository).findByCategory("video-games", 5);
    }

    // Tests for CheckAnswer
    @Test
    void shouldReturnTrueAnswerIsCorrect() {
        TriviaQuestion q1 = new TriviaQuestion();
        q1.setId((long)1);
        TriviaChoice c1 = new TriviaChoice();
        c1.setId((long)123);
        c1.setIsCorrect(true);
        q1.setChoices(List.of(c1));
        when(triviaQuestionRepository.findById(q1.getId()))
            .thenReturn(Optional.of(q1));
        Boolean result = service.checkAnswer(q1.getId(), c1.getId());
        assertTrue(result);
        verify(triviaQuestionRepository).findById(q1.getId());
    }

    @Test
    void shouldReturnFalseAnswerIsNotCorrect() {
        TriviaQuestion q1 = new TriviaQuestion();
        q1.setId(1L);
        TriviaChoice c1 = new TriviaChoice();
        c1.setId(123L);
        c1.setIsCorrect(false);
        q1.setChoices(List.of(c1));
        when(triviaQuestionRepository.findById(q1.getId()))
            .thenReturn(Optional.of(q1));
        Boolean result = service.checkAnswer(q1.getId(), c1.getId());
        assertFalse(result);
    }

    @Test
    void shouldThrowExceptionWhenQuestionNotFound() {
        when(triviaQuestionRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.checkAnswer(1L, 123L)
        );

        assertEquals("Question not found", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenChoiceNotFound() {
        TriviaQuestion q1 = new TriviaQuestion();
        q1.setId(1L);
        q1.setChoices(List.of());

        when(triviaQuestionRepository.findById(q1.getId()))
                .thenReturn(Optional.of(q1));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.checkAnswer(q1.getId(), 123L)
        );

        assertEquals("Choice not found", exception.getMessage());
    }

    // End of tests for checkAnswer()
}


