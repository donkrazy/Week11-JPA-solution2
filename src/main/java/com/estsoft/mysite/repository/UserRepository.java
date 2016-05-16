package com.estsoft.mysite.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.estsoft.mysite.domain.Guestbook;
import com.estsoft.mysite.domain.User;

@Repository
public class UserRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void save( User user ) {
		em.persist( user );
	}
	
	public User findByEmailAndPassword( String email, String password ) {
		String jpql = "select u from User u where u.email = :email and u.password = :password";
		List<User> list = 
		em.
		createQuery( jpql, User.class ).
		setParameter( "email", email ).
		setParameter( "password", password ).
		getResultList();
		
		if( list.size() == 1 ) {
			return null;
		}
		
		return list.get( 0 );
	}

	public User findByEmail( String email ) {
		String jpql = "select u from User u where u.email = :email";
		List<User> list = 
		em.
		createQuery( jpql, User.class ).
		setParameter( "email", email ).
		getResultList();
		
		if( list.size() != 1 ) {
			return null;
		}
		
		return list.get( 0 );
	}
	
}
