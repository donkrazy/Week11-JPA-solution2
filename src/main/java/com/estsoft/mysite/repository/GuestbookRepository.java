package com.estsoft.mysite.repository;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.estsoft.mysite.domain.Guestbook;
import com.mysema.query.jpa.impl.JPAQuery;
import static com.estsoft.mysite.domain.QGuestbook.guestbook;

@Repository
public class GuestbookRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void save( Guestbook guestbook ) {
		guestbook.setRegDate( new Date() );
		em.persist( guestbook );
	}
	
	public Boolean remove( Guestbook gb ) {
		/* JPQL		
		TypedQuery<Guestbook> query = 
				em.createQuery( 
		"select gb from Guestbook gb where gb.no = :no and gb.password = :password", Guestbook.class );
		query.setParameter( "no", guestbook.getNo() );
		query.setParameter( "password", guestbook.getPassword() );
		List<Guestbook> list = query.getResultList();
		*/
		
		// QueryDSL
		Guestbook result = new JPAQuery( em ).
			from( guestbook ).
			where( guestbook.no.eq( gb.getNo() ), 
				   guestbook.password.eq( gb.getPassword() ) ).
			singleResult( guestbook );

		if( result == null ) {
			return false;
		}
		
		em.remove( result );
		return true;
	}
	
	public List<Guestbook> findAll() {
		/* JPQL
		TypedQuery<Guestbook> query = 
				em.createQuery( "select gb from Guestbook gb order by gb.regDate desc", Guestbook.class );
		List<Guestbook> list = query.getResultList();
		*/
		
		// QueryDSL
		return new JPAQuery( em ).
			from( guestbook ).
			orderBy( guestbook.regDate.desc() ).
			list( guestbook );
	}
}