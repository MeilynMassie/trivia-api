package com.mjm.api.trivia.repository;

import com.mjm.api.trivia.model.TriviaQuestion;

import java.util.List;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TriviaQuestionRepository extends JpaRepository<TriviaQuestion, Long> {
    @Query("SELECT DISTINCT q.category FROM TriviaQuestion q")
    List<String> findAllCategories();

    // Page<TriviaQuestion> findByCategory(String category, Pageable pageable);
    @Query(value = """
    SELECT *
    FROM trivia_question
    WHERE category = :category
    ORDER BY RANDOM()
    LIMIT :limit
    """, nativeQuery = true)
    List<TriviaQuestion> findByCategory(
            @Param("category") String category,
            @Param("limit") int limit);
}