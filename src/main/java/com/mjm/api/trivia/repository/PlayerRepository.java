package com.mjm.api.trivia.repository;

import com.mjm.api.trivia.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}