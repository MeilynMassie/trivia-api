package com.mjm.api.trivia.repository;

import com.mjm.api.trivia.model.TriviaQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TriviaRepository extends JpaRepository<TriviaQuestion, Long> {
    @Query("SELECT DISTINCT q.category FROM TriviaQuestion q")
    List<String> findAllCategories();

    @Query(value = "SELECT * FROM trivia_question WHERE category = :category ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<TriviaQuestion> findRandomQuestions(@Param("limit") int limit, @Param("category") String category);
}