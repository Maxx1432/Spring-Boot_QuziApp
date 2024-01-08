package com.maxx.quizApp.service;

import com.maxx.quizApp.dao.QuestionDao;
import com.maxx.quizApp.dao.QuizDao;
import com.maxx.quizApp.model.Question;
import com.maxx.quizApp.model.QuestionWrapper;
import com.maxx.quizApp.model.Quiz;
import com.maxx.quizApp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao qusDao;

    public ResponseEntity<String> createQuiz(String category, int numOfQ, String title) {

        List<Question> questions = qusDao.findRandomQuestionsByCategory(category,numOfQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();
        for (Question q : questionFromDB)
        {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(),
                    q.getOption2(), q.getOption3(), q.getOption4());
            questionForUser.add(qw);
        }

        return new ResponseEntity<>(questionForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        Map<Integer, String> ansMap = new HashMap<>();
        for(Question qus : questions)
        {
            ansMap.put(qus.getId(),qus.getRightAnswer());
        }

        int right =0;
         int i= 0;
        
       Map<Integer, Question> questionMap = new HashMap<>();
	    for (Question question : questions) {
	        questionMap.put(question.getId(), question);
	    }

	    // Iterate through the responses
	    for (Response response : responses) {
	        // Retrieve the corresponding question using the response's question ID
	        Question question = questionMap.get(response.getId());
	        if (question != null && response.getResponse().equals(question.getRightAnswer())) {
	            right++; // Increment the correct response count
	        }
	    }

        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
