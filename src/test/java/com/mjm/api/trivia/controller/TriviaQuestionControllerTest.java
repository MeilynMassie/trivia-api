package com.mjm.api.trivia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mjm.api.trivia.model.TriviaQuestion;
import com.mjm.api.trivia.service.TriviaQuestionService;

@ExtendWith(MockitoExtension.class)
class TriviaQuestionControllerTest {

    @Mock
    private TriviaQuestionService triviaQuestionService;

    @InjectMocks
    private TriviaQuestionController controller;

    @Test
    void shouldGetAllCategories() {
        List<String> categories = List.of("general", "science");
        when(triviaQuestionService.getCategories()).thenReturn(categories);

        List<String> result = controller.getAllCategories();

        assertEquals(categories, result);
        verify(triviaQuestionService).getCategories();
    }

    @Test
    void shouldGetQuestions() {
        TriviaQuestion q1 = new TriviaQuestion();
        q1.setId(1L);
        q1.setCategory("general");

        TriviaQuestion q2 = new TriviaQuestion();
        q2.setId(2L);
        q2.setCategory("general");

        List<TriviaQuestion> questions = List.of(q1, q2);
        when(triviaQuestionService.getQuestions("general", 10)).thenReturn(questions);

        List<TriviaQuestion> result = controller.getQuestions("general", 10);

        assertEquals(questions, result);
        verify(triviaQuestionService).getQuestions("general", 10);
    }

    @Test
    void shouldCheckAnswer() {
        when(triviaQuestionService.checkAnswer(10L, 20L)).thenReturn(Boolean.TRUE);

        Boolean result = controller.checkAnswer(10L, 20L);

        assertEquals(Boolean.TRUE, result);
        verify(triviaQuestionService).checkAnswer(10L, 20L);
    }
}
