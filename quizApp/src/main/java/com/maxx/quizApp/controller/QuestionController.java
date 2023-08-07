package com.maxx.quizApp.controller;

import com.maxx.quizApp.model.Question;
import com.maxx.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService qusService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return qusService.getAllQuestions();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return qusService.getQuestionByCategory(category);
    }

    @PostMapping(
            value = "add",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> addQuestion(@RequestBody Question qus){
        return qusService.addQuestion(qus);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id)
    {
        return qusService.deleteQuestion(id);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateQuestion(@RequestBody Question qus){
        return qusService.updateQuestion(qus);
    }
}
