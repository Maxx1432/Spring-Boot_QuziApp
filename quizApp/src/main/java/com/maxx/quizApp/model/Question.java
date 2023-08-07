package com.maxx.quizApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String category;
    String questionTitle;
    String difficultylevel;
    String option1;
    String option2;
    String option3;
    String option4;
    String rightAnswer;


}
