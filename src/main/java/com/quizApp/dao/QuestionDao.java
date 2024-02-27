package com.quizApp.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.quizApp.entities.Quesitons;


@Repository
public interface QuestionDao extends JpaRepository<Quesitons, Integer> {
	// making customised method by category fetching...
	
	public List<Quesitons> getByCategory(String cat);

	@Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
	public List<Quesitons> findRandQuesByCateg(String category, int numQ);
}
