package com.demo.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.quizapp.dao.QuestionDao;
import com.demo.quizapp.dao.QuizDao;
import com.demo.quizapp.model.Question;
import com.demo.quizapp.model.QuestionWrapper;
import com.demo.quizapp.model.Quiz;
import com.demo.quizapp.model.Response;

@Service
public class QuizService {
    
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numOfQuestion, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numOfQuestion);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDao.save(quiz); 

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDao.findById(id); 
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }


        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer quizId, List<Response> responses) {
        
        int totalScore = 0;
        Optional<Quiz> quiz = quizDao.findById(quizId);
        List<Question> questions = quiz.get().getQuestions();
        int i = 0;

        for (Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getRightAnswer())) {
                totalScore++;
            }
            i++;
        }

        return new ResponseEntity<>(totalScore, HttpStatus.OK);
    }
}
