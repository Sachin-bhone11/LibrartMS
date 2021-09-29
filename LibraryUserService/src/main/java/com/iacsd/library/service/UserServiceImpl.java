package com.iacsd.library.service;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iacsd.library.exception.UserAlreadyExistsException;
import com.iacsd.library.exception.UserNotFoundException;
import com.iacsd.library.model.User;
import com.iacsd.library.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	public User findByEmailAndPassword(String email, String password) throws UserNotFoundException {
				return userRepository.findByEmailAndUserPassword(email, password);
	}

	public boolean saveUser(User user) throws UserAlreadyExistsException {
		try
    	{
    		User findById = userRepository.findByEmail(user.getEmail());
    		if (findById!=null) {
    	    	throw new UserAlreadyExistsException("Cannot Register User");
			}
    		
    		if (findById == null) {
    			User userAdded = userRepository.save(user);
    	    	if(userAdded!=null)
    	    	{
    	    		return  true;
    	    	}
			}
    		
    	}
    	catch(NoSuchElementException exception)
    	{
    		exception.printStackTrace();
    		User userAdded = userRepository.save(user);
	    	if(userAdded!=null)
	    	{
	    		return  true;
	    	}
    	}
		return false;
	}

	
	public User findByEmail(String email) {
			return userRepository.findByEmail(email);
	}

}
