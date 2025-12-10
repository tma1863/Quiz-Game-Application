package com.demo.quizapp.service;

import com.demo.quizapp.dao.QuestionDao;
import com.demo.quizapp.model.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // Same functions with Component, but since we are in service layer, we use this :) ?
public class QuestionService {
    
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);   
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);  
        }  
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try{
            questionDao.save(question);
            return new ResponseEntity<>("Successfully add the new question", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to add the question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    public ResponseEntity<String> updateQuestionById(Integer id, Question question) {
        try{   
            Question existingQuestion = questionDao.findById(id).orElseThrow(() -> new RuntimeException("Question ID " + id + " not found"));
            
            // update fields
            existingQuestion.setQuestionTitle(question.getQuestionTitle());
            existingQuestion.setOption1(question.getOption1());
            existingQuestion.setOption2(question.getOption2());
            existingQuestion.setOption3(question.getOption3());
            existingQuestion.setOption4(question.getOption4());
            existingQuestion.setRightAnswer(question.getRightAnswer());
            existingQuestion.setDifficultyLevel(question.getDifficultyLevel());
            existingQuestion.setCategory(question.getCategory());

            questionDao.save(existingQuestion);
            return new ResponseEntity<>("Question ID " + id + " updated successfully", HttpStatus.ACCEPTED);
        
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to update the question ID " + id, HttpStatus.NOT_FOUND);
        
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update the question ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    public ResponseEntity<String> deleteQuestionById(Integer id) {
        try {
            
            if (!questionDao.existsById(id)) {
                return new ResponseEntity<>("Question ID " + id + " not found", HttpStatus.NOT_FOUND);
            }

            questionDao.deleteById(id);
            return new ResponseEntity<>("Successfully delete questionID " + id, HttpStatus.ACCEPTED);
        
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete questionID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
