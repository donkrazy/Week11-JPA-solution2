package com.estsoft.mysite.repository;

import static com.estsoft.mysite.domain.QUser.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.estsoft.mysite.domain.User;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class UserRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void save( User user ) {
		em.persist( user );
	}
	
	public User findByEmailAndPassword( String email, String password ) {
/*		JPQL

		String jpql = "selct u from User u where u.email = :email and u.password = :password";
		List<User> list = 
		em.
		createQuery( jpql, User.class ).
		setParameter( "email", email ).
		setParameter( "password", password ).
		getResultList();
		
*/
		// QueryDSL
		return new JPAQuery( em ).
			from( user ).
			where( user.email.eq( email ), user.password.eq( password ) ).
			singleResult( user );
	}

	public User findByEmail( String email ) {
/*		JPQL
 		
		String jpql = "select u from User u where u.email = :email";
		List<User> list = 
		em.
		createQuery( jpql, User.class ).
		setParameter( "email", email ).
		getResultList();
*/
		// QueryDSL
		return new JPAQuery( em ).
			from( user ).
			where( user.email.eq( email ) ).
			singleResult( user );
	}
}
