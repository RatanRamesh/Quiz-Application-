package com.quizApp.serviceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizApp.dao.QuestionDao;
import com.quizApp.entities.Quesitons;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionDao questionDao;
	
	public List<Quesitons> getAllQuestions() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return questionDao.findAll();
	}
	
	public Quesitons findByQuestionId(int id) {
		return this.questionDao.findById(id).orElse(null);
		
	}
	
	public List<Quesitons> getByCategory(String cat){
		return questionDao.getByCategory(cat);
	}
	
	public Quesitons addQuestion(Quesitons quest) {
		return this.questionDao.save(quest);
		
	}
	
	public List<Quesitons> addMultipleQuestions(List<Quesitons> quest){
		return this.questionDao.saveAll(quest);
	}
	
	public Quesitons updateQuestion(Quesitons que, int id) {
		return this.questionDao.save(que);
	}
	
	public void deleteQuestion(int id) {
		if(questionDao.existsById(id)) {
			this.questionDao.deleteById(id);
		}
		else {
			throw new IllegalArgumentException("Question with ID " + id + " does not exist");
		}
		 
	}

}
