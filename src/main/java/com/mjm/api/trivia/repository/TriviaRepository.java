package com.mjm.api.trivia.repository;

import com.mjm.api.trivia.model.TriviaQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TriviaRepository extends JpaRepository<TriviaQuestion, Long> {
}