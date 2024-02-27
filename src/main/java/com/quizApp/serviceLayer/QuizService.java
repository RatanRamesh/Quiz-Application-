package com.quizApp.serviceLayer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizApp.dao.QuestionDao;
import com.quizApp.dao.QuizDao;
import com.quizApp.entities.Quesitons;
import com.quizApp.entities.QuestionWrapper;
import com.quizApp.entities.Quiz;
import com.quizApp.entities.Response;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao quesDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Quesitons> question = quesDao.findRandQuesByCateg(category, numQ);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestion(question);
		
		quizDao.save(quiz);
					
		return ResponseEntity.status(HttpStatus.CREATED).body("Quiz created successfully");
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Quiz q = quizDao.findById(id).orElse(null);
		List<Quesitons> quesFromDb = q.getQuestion();
		List<QuestionWrapper> quesForUser = new ArrayList<>();
		for (Quesitons questions : quesFromDb) {
			QuestionWrapper qw = new QuestionWrapper(questions.getId(), questions.getQuestionTitle(), questions.getOption1(), questions.getOption2(), questions.getOption3(), questions.getOption4());
			quesForUser.add(qw);
			
			
			
		}
		return new ResponseEntity<>(quesForUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calResult(Integer id, List<Response> responses) {
		Quiz quiz = quizDao.findById(id).get();
		List<Quesitons> questions = quiz.getQuestion();
		
		int right = 0;
		int i = 0;
		for (Response reponse : responses ) {
			if(reponse.getResponse().equals(questions.get(i).getRightAnswer()))
				right++;
			
			i++;
		} 
		
		return new ResponseEntity<>(right, HttpStatus.OK);
	}
	
}
