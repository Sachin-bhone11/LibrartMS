package com.iacsd.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iacsd.library.model.BookRequest;

@Repository
public interface BookRequestRepository extends JpaRepository<BookRequest, Long>{
	
	List<BookRequest> findByStatus(String status);
		
	List<BookRequest> findByUserIdAndStatus(Long userId, String status);
	
	List<BookRequest> findByUserIdAndStatusAndBookId(Long userId, String status, Long bookId);
	
}
