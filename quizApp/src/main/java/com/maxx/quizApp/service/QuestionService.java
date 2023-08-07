package com.maxx.quizApp.service;

import com.maxx.quizApp.model.Question;
import com.maxx.quizApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao qusDao;
    public ResponseEntity <List<Question>> getAllQuestions() {
       try{
           return new ResponseEntity<>(qusDao.findAll(), HttpStatus.OK);
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public  ResponseEntity<List<Question>> getQuestionByCategory(String category){
        try {
            return new ResponseEntity<>(qusDao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> addQuestion(Question qus) {
        try {
            qusDao.save(qus);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        try {
            qusDao.deleteById(id);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateQuestion(Question qus) {
        try {
            qusDao.saveAndFlush(qus);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }
}
