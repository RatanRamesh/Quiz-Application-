package com.quizApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quizApp.entities.QuestionWrapper;
import com.quizApp.entities.Response;
import com.quizApp.serviceLayer.QuizService;


@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired 
	private QuizService quizService; 
		
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
		return quizService.createQuiz(category, numQ,title);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
		return quizService.getQuizQuestions(id);
	}
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> getSubmit(@PathVariable Integer id, @RequestBody List<Response> response){
		return quizService.calResult(id, response);
	}
	
	
}
