package com.quizApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quizApp.entities.Quesitons;
import com.quizApp.serviceLayer.QuestionService;

@RestController
public class Controller {
	
	@Autowired 
	private QuestionService questionService;
	
	@GetMapping("/AllQuestions")
	public ResponseEntity<List<Quesitons>> getAllQuestions() {
		try {
			List<Quesitons> ques1 = this.questionService.getAllQuestions();
			if(ques1.size() < 0 ) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();					
			}
			return ResponseEntity.of(Optional.of(ques1)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	@GetMapping("/Question/{id}")
	public ResponseEntity<Quesitons> getQuestion(@PathVariable String id) {
		try {
			Quesitons ques2 = this.questionService.findByQuestionId(Integer.parseInt(id));
			if(ques2 == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.of(Optional.of(ques2));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/Question/category/{category}")
	public ResponseEntity<List<Quesitons>> getByCategory(@PathVariable("category") String cat){
		try {
			List<Quesitons> ques3 = this.questionService.getByCategory(cat);
			if(ques3.size() <= 0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();		
			}
			return ResponseEntity.of(Optional.of(ques3));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();		
	}


	@PostMapping("/addQuestions")
	public ResponseEntity<List<Quesitons>> addQuestions(@RequestBody List<Quesitons> quest){
		try {
			if(quest.size() <=0) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();	
			}
			List<Quesitons> ques1 = this.questionService.addMultipleQuestions(quest);
			return ResponseEntity.of(Optional.of(ques1));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping("/addQuestion")
	public ResponseEntity<Quesitons> addQuestion(Quesitons quest) {
		try {
			Quesitons ques2 = this.questionService.addQuestion(quest);
			return ResponseEntity.of(Optional.of(ques2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
		
//		Tried to add single question and Multiple Questions in a single PostMethod but somehow didn't work
				
//		if (quest instanceof Quesitons) {
//			
//			Quesitons singleQues = (Quesitons)quest;
//			Quesitons saveQuestion = questionService.addQuestion(singleQues);
//			return ResponseEntity.status(HttpStatus.CREATED).body(saveQuestion);
//		}
//		
//		else if(quest instanceof List<?>) {
//			 @SuppressWarnings("unchecked")
//			 List<Quesitons> multipleQuesitions = (List<Quesitons>) quest;
//			 List<Quesitons> saveMultipleQuesitions = questionService.addMultipleQuestions(multipleQuesitions);
//			 return ResponseEntity.status(HttpStatus.CREATED).body(saveMultipleQuesitions);	 		
//		}
//		
//		else {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Data");
//		}
//		
	
	@PutMapping("/updateQuestion/{id}")
	public ResponseEntity<String> updateQuestion(@RequestBody Quesitons ques, @PathVariable int id ){
		try {
			this.questionService.updateQuestion(ques,id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Data updated Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid Id");
		
	}
	
	@DeleteMapping("/deleteQuestion/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable int id){
		try {
			this.questionService.deleteQuestion(id);
			return ResponseEntity.status(HttpStatus.OK).body("Data Deleted SuccessFully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid Id");
	}
}
