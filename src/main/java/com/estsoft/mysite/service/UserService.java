package com.estsoft.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.mysite.dao.UserDao;
import com.estsoft.mysite.domain.User;
import com.estsoft.mysite.repository.UserRepository;
import com.estsoft.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRepository userRepository;
	
	public void join( User user ) {
		userRepository.save( user );
	}
	
	public UserVo login( UserVo vo ) {
		UserVo authUser = userDao.get( vo );
		return authUser;
	}
	
	public User getUser( String email ) {
		User user = userRepository.findByEmail( email );
		return user;
	}
}
