package com.quizApp.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String title;
	
	@ManyToMany
	private List<Quesitons> question;

	
	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quiz(int id, String title, List<Quesitons> question) {
		super();
		this.id = id;
		this.title = title;
		this.question = question;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Quesitons> getQuestion() {
		return question;
	}

	public void setQuestion(List<Quesitons> question) {
		this.question = question;
	}
	
}
