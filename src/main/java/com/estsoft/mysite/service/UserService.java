package com.estsoft.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.estsoft.mysite.domain.User;
import com.estsoft.mysite.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void join( User user ) {
		userRepository.save( user );
	}
	
	public User login( User vo ) {
		User authUser = userRepository.findByEmailAndPassword(vo.getEmail(), vo.getPassword() );
		return authUser;
	}
	
	public User getUser( String email ) {
		User user = userRepository.findByEmail( email );
		return user;
	}
}