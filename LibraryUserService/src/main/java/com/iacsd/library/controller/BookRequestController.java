package com.iacsd.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iacsd.library.DTO.UserBorrowBookDetails;
import com.iacsd.library.model.Book;
import com.iacsd.library.model.BookRequest;
import com.iacsd.library.repository.BookRepository;
import com.iacsd.library.repository.BookRequestRepository;
import com.iacsd.library.repository.UserRepository;
import com.iacsd.library.service.BookRequestService;
import com.iacsd.library.service.UserService;

@RestController
@RequestMapping("/api/v1/bookrequest")
@CrossOrigin(origins = "http://localhost:3000")
public class BookRequestController {

	@Autowired
	private BookRequestService bookRequestService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookRequestRepository bookRequestRepository;
	
	@GetMapping
	public ResponseEntity<List<BookRequest>> booksRequests () {
		List<BookRequest> bookRequests = bookRequestService.getAllRequest();
		return new ResponseEntity<List<BookRequest>>(bookRequests, HttpStatus.OK);
		
	}
	

	
	@GetMapping("/userbookrequests")
	public ResponseEntity<List<UserBorrowBookDetails>> userBookRequests () {
		List<BookRequest> bookRequests = bookRequestService.getAllRequest();
		//UserBorrowBookDetails userBorrowBookDetails = new UserBorrowBookDetails();
		List<UserBorrowBookDetails> userBorrowBookDetailsList = new ArrayList<UserBorrowBookDetails>();
		for (BookRequest bookRequest : bookRequests) {
			UserBorrowBookDetails userBorrowBookDetails = new UserBorrowBookDetails(bookRequest,
																					userRepository.findById(bookRequest.getUserId()).get(),
																					bookRepository.findById(bookRequest.getBookId()).get());
			userBorrowBookDetailsList.add(userBorrowBookDetails);
		}
		return new ResponseEntity<List<UserBorrowBookDetails>>(userBorrowBookDetailsList, HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<BookRequest> saveBookRequest (@RequestBody BookRequest bookRequet) {
		BookRequest bookRequest = bookRequestService.cretaeRequest(bookRequet);
		return new ResponseEntity<BookRequest>(bookRequest, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<BookRequest> updateBookRequest (@RequestParam String requestId, @RequestParam String status) {
		BookRequest request = bookRequestRepository.findById(Long.parseLong(requestId)).get();
		request.setStatus(status);
		BookRequest bookRequest = bookRequestService.updateRequest(request);
		return new ResponseEntity<BookRequest>(bookRequest, HttpStatus.OK);
	}
	
//	@GetMapping("/byuseridandstatus")
//	public ResponseEntity<List<Book>> issuedBooksByUserId (@RequestParam String userid, @RequestParam String status) {
//		List<Book> booksByUserIdAndRequestStatus = bookRequestService.getBooksByUserIdAndRequestStatus(userid, status);
//		return new ResponseEntity<>(booksByUserIdAndRequestStatus,HttpStatus.OK);
//	}
	
	@PutMapping("/admin/action")
	public ResponseEntity<BookRequest> adminActionOnRequest (@RequestParam String reruestId, @RequestParam String action) {
		BookRequest actionOnBookRequest = bookRequestService.adminActionOnBookRequest(reruestId, action);
		return new ResponseEntity<BookRequest>(actionOnBookRequest,HttpStatus.OK);
	}
	
	@GetMapping("/userbookrequestsbystatus")
	public ResponseEntity<List<UserBorrowBookDetails>> userBooks ( @RequestParam String status) {
		List<BookRequest> requestByUserIdAndStatusAndBookId = bookRequestService.getRequestByRequestStatus(status);
		List<UserBorrowBookDetails> userBorrowBookDetailsList = new ArrayList<UserBorrowBookDetails>();
		for (BookRequest bookRequest : requestByUserIdAndStatusAndBookId) {
			UserBorrowBookDetails userBorrowBookDetail = new UserBorrowBookDetails(bookRequest,
																					userRepository.findById(bookRequest.getUserId()).get(),
																					bookRepository.findById(bookRequest.getBookId()).get());
			userBorrowBookDetailsList.add(userBorrowBookDetail);
		}
		return new ResponseEntity<List<UserBorrowBookDetails>>(userBorrowBookDetailsList, HttpStatus.OK);
		
	}
}
