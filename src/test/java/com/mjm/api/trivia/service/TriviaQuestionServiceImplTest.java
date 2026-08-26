// package com.mjm.api.trivia.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.io.ByteArrayInputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Nested;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.ArgumentCaptor;
// import org.mockito.ArgumentMatchers;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.core.io.support.ResourcePatternResolver;
// import org.springframework.core.io.Resource;

// import com.fasterxml.jackson.core.type.TypeReference;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.mjm.api.trivia.dto.TriviaQuestionDTO;
// import com.mjm.api.trivia.model.TriviaChoice;
// import com.mjm.api.trivia.model.TriviaQuestion;
// import com.mjm.api.trivia.repository.TriviaQuestionRepository;
// import com.mjm.api.trivia.service.impl.TriviaQuestionServiceImpl;

// @ExtendWith(MockitoExtension.class)
// public class TriviaQuestionServiceImplTest {
//     @Mock
//     private TriviaQuestionRepository triviaQuestionRepository;

//     @Mock
//     private ObjectMapper objectMapper;

//     @Mock
//     private ResourcePatternResolver resolver;

//     @InjectMocks
//     private TriviaQuestionServiceImpl service;

//     @Test
//     void returnAllCategories() {
//         List<String> expected = List.of(
//                                     "celebrities", "world", "history", "rated", "brain-teasers",
//                                     "for-kids", "video-games", "literature", "television", "animals",
//                                     "people", "science-technology", "sports", "music", "entertainment", 
//                                     "newest", "religion-faith", "geography", "movies", "hobbies",
//                                     "humanities", "general");

//         when(triviaQuestionRepository.findAllCategories())
//                 .thenReturn(expected);

//         List<String> result = service.getCategories();

//         assertEquals(expected, result);

//         verify(triviaQuestionRepository).findAllCategories();
//         }

//     @Test
//     void returnTwoQuestionsForVideoGames() {
//         List<TriviaQuestion> expected = new ArrayList<>();
//         for (int i = 0; i < 20; i++) {
//             TriviaQuestion q1 = new TriviaQuestion();
//             q1.setQuestion("Question?");
//             q1.setCategory("video-games");
//             expected.add(q1);
//         }
//         when(triviaQuestionRepository.findByCategory("video-games", 2))
//             .thenReturn(expected);
//         List<TriviaQuestion> result = service.getQuestions("video-games", 2);
//         assertEquals(expected, result);
//         verify(triviaQuestionRepository).findByCategory("video-games", 2);
//     }

//     @Nested
//     class CheckAnswerTests {
//         TriviaQuestion q1;
//         TriviaChoice c1;


//         @BeforeEach 
//         void init() {
//             q1 = new TriviaQuestion();
//             q1.setId(1L);
//             c1 = new TriviaChoice();
//             c1.setId(123L);

//             when(triviaQuestionRepository.findById(q1.getId()))
//                 .thenReturn(Optional.of(q1));
//         }

//         @Test
//         void shouldReturnTrueAnswerIsCorrect() {
//             c1.setIsCorrect(true);
//             q1.setChoices(List.of(c1));
//             boolean result = service.checkAnswer(q1.getId(), c1.getId());
//             assertTrue(result);
//             verify(triviaQuestionRepository).findById(q1.getId());
//         }

//         @Test
//         void shouldReturnFalseAnswerIsNotCorrect() {
//             c1.setIsCorrect(false);
//             q1.setChoices(List.of(c1));
//             boolean result = service.checkAnswer(q1.getId(), c1.getId());
//             assertFalse(result);
//         }

//         @Test
//         void shouldThrowExceptionWhenQuestionNotFound() {
//             when(triviaQuestionRepository.findById(1L))
//                     .thenReturn(Optional.empty());

//             RuntimeException exception = assertThrows(
//                     RuntimeException.class,
//                     () -> service.checkAnswer(1L, 123L)
//             );

//             assertEquals("Question not found", exception.getMessage());
//         }

//         @Test
//         void shouldThrowExceptionWhenChoiceNotFound() {
//             q1.setChoices(List.of());

//             RuntimeException exception = assertThrows(
//                     RuntimeException.class,
//                     () -> service.checkAnswer(q1.getId(), 123L)
//             );

//             assertEquals("Choice not found", exception.getMessage());
//         }
//     }

//     @Nested
//     class LoadDataTests {
//         private Resource resource;
//         private Resource[] resources;
//         private TriviaQuestionDTO dto;
//         private List<TriviaQuestionDTO> testData;
//         private InputStream inputStream;
//         String path = "classpath:/data/*.json";


//         @BeforeEach
//         void init() throws IOException{
//             resource = mock(Resource.class);
//             inputStream = new ByteArrayInputStream(new byte[0]);
//             resources = new Resource[] { resource };
//             dto = new TriviaQuestionDTO();

//             dto.setQuestion("Q1");
//             dto.setCategory("testCategory");
//             dto.setAnswer("C1");
//             dto.setChoices(List.of("C1", "C2", "C3", "C4"));

//             testData = List.of(dto);

//             when(resolver.getResources(path))
//                 .thenReturn(resources);
//             when(resource.getFilename())
//                 .thenReturn("questions.json");
//             when(resource.getInputStream())
//                 .thenReturn(inputStream);
//             when(objectMapper.readValue(
//                     any(InputStream.class),
//                     ArgumentMatchers.<TypeReference<List<TriviaQuestionDTO>>>any()))
//                 .thenReturn(testData);
//         }
        
//         @Test
//         // Verifies resolver, mapper, save
//         void shouldLoadQuestionsAndSaveThem() throws IOException {
//             service.loadQuestions();

//             verify(resolver).getResources(path);
//             verify(resource).getInputStream();
            
//             verify(objectMapper).readValue(
//                 any(InputStream.class),
//                 ArgumentMatchers.<TypeReference<List<TriviaQuestionDTO>>>any());
//             verify(triviaQuestionRepository).save(any(TriviaQuestion.class));
//         }

//         @Test
//         // Test for data validation
//         void shouldCreateQuestionWithCorrectChoices() {

//             service.loadQuestions();

//             ArgumentCaptor<TriviaQuestion> questionCaptor =
//                     ArgumentCaptor.forClass(TriviaQuestion.class);

//             verify(triviaQuestionRepository).save(questionCaptor.capture());

//             TriviaQuestion savedQuestion = questionCaptor.getValue();

//             assertEquals("Q1", savedQuestion.getQuestion());
//             assertEquals("testCategory", savedQuestion.getCategory());

//             assertEquals(4, savedQuestion.getChoices().size());

//             TriviaChoice choice1 = savedQuestion.getChoices().get(0);
//             TriviaChoice choice2 = savedQuestion.getChoices().get(1);
//             TriviaChoice choice3 = savedQuestion.getChoices().get(2);
//             TriviaChoice choice4 = savedQuestion.getChoices().get(3);

//             assertEquals("C1", choice1.getChoiceText());
//             assertTrue(choice1.getIsCorrect());
//             assertEquals((short)1, choice1.getDisplayOrder());

//             assertEquals("C2", choice2.getChoiceText());
//             assertFalse(choice2.getIsCorrect());
//             assertEquals((short)2, choice2.getDisplayOrder());

//             assertEquals("C3", choice3.getChoiceText());
//             assertFalse(choice3.getIsCorrect());
//             assertEquals((short)3, choice3.getDisplayOrder());

//             assertEquals("C4", choice4.getChoiceText());
//             assertFalse(choice4.getIsCorrect());
//             assertEquals((short)4, choice4.getDisplayOrder());
//         }
//     }
// }
