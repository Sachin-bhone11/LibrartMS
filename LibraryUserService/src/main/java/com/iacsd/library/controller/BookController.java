package com.iacsd.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.iacsd.library.repository.UserRepository;
import com.iacsd.library.service.BookRequestService;
import com.iacsd.library.service.BookServicempl;

@RestController
@RequestMapping("/api/v1/book")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

	@Autowired
	private BookServicempl bookService;
	
	@Autowired
	private BookRequestService bookRequestService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping
	public ResponseEntity<List<Book>> books () {
		List<Book> books = bookService.findBooks();
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<Book> saveBook (@RequestBody Book book) {
		Book saveBook = bookService.saveBook(book);
		return new ResponseEntity<Book>(saveBook, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/admin")
	public ResponseEntity<Book> updateBook (@RequestBody Book book) {
		Book saveBook = bookService.saveBook(book);
		return new ResponseEntity<Book>(saveBook, HttpStatus.OK);
	}
	
	@DeleteMapping("/admin")
	public ResponseEntity<?> deleteBook (@RequestParam String bookId) {
		//bookService.deleteBook(book);
		bookRepository.deleteById(Long.parseLong(bookId));
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/userbooks")
	public ResponseEntity<List<UserBorrowBookDetails>> userBooks (@RequestParam String userId, @RequestParam String status) {
		List<BookRequest> requestByUserIdAndStatus = bookRequestService.getRequestByUserIdAndRequestStatus(userId, status);
		//UserBorrowBookDetails userBorrowBookDetails = new UserBorrowBookDetails();
		List<UserBorrowBookDetails> userBorrowBookDetailsList = new ArrayList<UserBorrowBookDetails>();
		for (BookRequest bookRequest : requestByUserIdAndStatus) {
			UserBorrowBookDetails userBorrowBookDetails = new UserBorrowBookDetails(bookRequest,
																					userRepository.findById(bookRequest.getUserId()).get(),
																					bookRepository.findById(bookRequest.getBookId()).get());
			userBorrowBookDetailsList.add(userBorrowBookDetails);
		}
		return new ResponseEntity<List<UserBorrowBookDetails>>(userBorrowBookDetailsList, HttpStatus.OK);
		
	}
	
	
}
