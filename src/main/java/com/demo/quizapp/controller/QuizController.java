package com.demo.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.quizapp.service.QuizService;
import com.demo.quizapp.model.QuestionWrapper;
import com.demo.quizapp.model.Response;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numOfQuestion, @RequestParam String title) { // use @RequestParam to accept URL variables
        return quizService.createQuiz(category, numOfQuestion, title);
    }

    @GetMapping("get/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int quizId) {
        return quizService.getQuizQuestions(quizId);
    }

    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer quizId, @RequestBody List<Response> responses) {
        return quizService.calculateResult(quizId, responses);
    }
}
