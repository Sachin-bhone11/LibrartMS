package com.iacsd.library.service;

import com.iacsd.library.exception.UserAlreadyExistsException;
import com.iacsd.library.exception.UserNotFoundException;
import com.iacsd.library.model.User;

public interface UserService {

    public User findByEmailAndPassword(String userId, String password) throws UserNotFoundException;

    boolean saveUser(User user) throws UserAlreadyExistsException;
    
    public User findByEmail(String email);
	
}
