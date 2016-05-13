package com.estsoft.mysite.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import com.estsoft.mysite.domain.Guestbook;

@Repository
public class GuestbookRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void save( Guestbook guestbook ) {
		guestbook.setRegDate( new Date() );
		em.persist( guestbook );
	}
	
	public Boolean remove( Guestbook guestbook ) {
		TypedQuery<Guestbook> query = 
				em.createQuery( 
		"select gb from Guestbook gb where gb.no = :no and gb.password = :password", Guestbook.class );
		query.setParameter( "no", guestbook.getNo() );
		query.setParameter( "password", guestbook.getPassword() );
		
		List<Guestbook> list = query.getResultList();
		if( list.size() != 1 ) {
			return false;
		}

		em.remove( list.get( 0 ) );
		return true;
	}
	
	public List<Guestbook> findAll() {
		TypedQuery<Guestbook> query = 
				em.createQuery( "select gb from Guestbook gb order by gb.regDate desc", Guestbook.class );
		List<Guestbook> list = query.getResultList();
		return list;
	}
}
