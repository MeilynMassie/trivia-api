package com.mjm.api.trivia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trivia_choice")
public class TriviaChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id")
    private TriviaQuestion question;

    @Column(name = "choice_text")
    private String choiceText;

    @Column(name = "display_order")
    private Short displayOrder;

    @JsonIgnore
    @Column(name = "is_correct")
    private Boolean isCorrect;
}