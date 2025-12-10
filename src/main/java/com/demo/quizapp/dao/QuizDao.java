package com.demo.quizapp.dao;

import com.demo.quizapp.model.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

    
} 
