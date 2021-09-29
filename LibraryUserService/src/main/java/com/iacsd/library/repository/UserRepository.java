package com.iacsd.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iacsd.library.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

	User findByEmailAndUserPassword(String email, String userPassword);
	
	User findByEmail(String email);
	
}
