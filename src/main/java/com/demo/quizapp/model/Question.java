package com.demo.quizapp.model;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@Data
@Entity
public class Question { // class name = table name in database
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // set id column as primary key 
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultyLevel;
    private String category;

}

