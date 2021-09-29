package com.iacsd.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iacsd.library.DTO.UserBorrowBookDetails;
import com.iacsd.library.model.BookRequest;
import com.iacsd.library.repository.BookRepository;
import com.iacsd.library.repository.BookRequestRepository;
import com.iacsd.library.repository.UserRepository;
import com.iacsd.library.service.BookRequestService;

@RestController
@RequestMapping("/api/v1/userBooks")
@CrossOrigin(origins = "http://localhost:3000")
public class UserBooksController {

	@Autowired
	private BookRequestService bookRequestService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookRequestRepository bookRequestRepository;
	
	@GetMapping
	public ResponseEntity<List<UserBorrowBookDetails>> userBooks (@RequestParam String userId, @RequestParam String status) {
		List<BookRequest> requestByUserIdAndStatusAndBookId = bookRequestService.getRequestByUserIdAndRequestStatus(userId, status);
		List<UserBorrowBookDetails> userBorrowBookDetailsList = new ArrayList<UserBorrowBookDetails>();
		for (BookRequest bookRequest : requestByUserIdAndStatusAndBookId) {
			UserBorrowBookDetails userBorrowBookDetail = new UserBorrowBookDetails(bookRequest,
																					userRepository.findById(Long.parseLong(userId)).get(),
																					bookRepository.findById(bookRequest.getBookId()).get());
			userBorrowBookDetailsList.add(userBorrowBookDetail);
		}
		return new ResponseEntity<List<UserBorrowBookDetails>>(userBorrowBookDetailsList, HttpStatus.OK);
		
	}
	
}
