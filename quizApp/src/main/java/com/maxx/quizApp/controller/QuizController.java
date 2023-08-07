package com.maxx.quizApp.controller;

import com.maxx.quizApp.model.Question;
import com.maxx.quizApp.model.QuestionWrapper;
import com.maxx.quizApp.model.Response;
import com.maxx.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numOfQ, @RequestParam String title){
      return quizService.createQuiz(category,numOfQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id)
    {
       return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response){
        return quizService.calculateResult(id, response);
    }
}
