package com.mjm.api.trivia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
public class TriviaQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trivia_seq")
    @SequenceGenerator(name = "trivia_seq", sequenceName = "trivia_question_id_seq", allocationSize = 1)
    private Long id;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(100)")
    private String category;

    @Column(columnDefinition = "TEXT")
    @Getter
    @Setter
    private String question;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(255)")
    private String answer;

    @Getter
    @Setter
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> choices;
}