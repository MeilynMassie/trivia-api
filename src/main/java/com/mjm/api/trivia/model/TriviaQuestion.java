package com.mjm.api.trivia.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TriviaQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trivia_seq")
    @SequenceGenerator(
            name = "trivia_seq",
            sequenceName = "trivia_question_id_seq",
            allocationSize = 1
    )
    private Long id;
    @Column(columnDefinition = "VARCHAR(100)")
    private String category;
    @Column(columnDefinition = "TEXT")
    private String question;
    @Column(columnDefinition = "VARCHAR(255)")
    private String answer;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> choices;
}