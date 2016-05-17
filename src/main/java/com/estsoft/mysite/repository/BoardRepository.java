package com.estsoft.mysite.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class BoardRepository {
	
	@PersistenceContext
	private EntityManager em;
}
