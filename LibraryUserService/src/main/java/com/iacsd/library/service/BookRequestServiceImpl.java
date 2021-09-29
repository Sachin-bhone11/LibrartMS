package com.iacsd.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iacsd.library.enums.BookRequestEnum;
import com.iacsd.library.model.Book;
import com.iacsd.library.model.BookRequest;
import com.iacsd.library.model.User;
import com.iacsd.library.repository.BookRepository;
import com.iacsd.library.repository.BookRequestRepository;

@Service
@Transactional
public class BookRequestServiceImpl implements BookRequestService{

	@Autowired
	private BookRequestRepository bookreRequestRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	
	public BookRequest cretaeRequest(BookRequest bookRequest) {
		return bookreRequestRepository.save(bookRequest);
	}

	@Override
	public BookRequest updateRequest(BookRequest bookRequest) {
		return bookreRequestRepository.save(bookRequest);
	}

	public List<BookRequest> getAllRequest() {
		return bookreRequestRepository.findAll();
	}
	
	public List<BookRequest> getRequestByUserIdAndRequestStatus(String userId, String status) {
		List<BookRequest> userIdAndStatus = bookreRequestRepository.findByUserIdAndStatus(Long.parseLong(userId) , status);
//		List<Book> books = new ArrayList<>();
//		for (BookRequest bookRequest : userIdAndStatus) {
//			books.add(bookRepository.findById(bookRequest.getBookId()).get());
//		}
		return userIdAndStatus;
	}

	@Override
	public List<BookRequest> getRequestByRequestStatus(String status) {
		List<BookRequest> findByStatus = bookreRequestRepository.findByStatus(status);
		return findByStatus;
	}

	@Override
	public BookRequest adminActionOnBookRequest(String requestId, String action) {
		BookRequest bookRequest = bookreRequestRepository.findById(Long.parseLong(requestId)).get();
		
		Book book = bookRepository.findById(bookRequest.getBookId()).get();
		//update book quantity based on action
		if (BookRequestEnum.ISSUED.toString().equalsIgnoreCase(action) && bookRequest.getStatus().equalsIgnoreCase(BookRequestEnum.NEW.toString())) {
			int updatedQuantity =  book.getQuantity() - bookRequest.getQuantity();
			if (updatedQuantity > 0) {
				book.setQuantity(updatedQuantity);
				bookRepository.save(book);
				bookRequest.setStatus(action);
				bookreRequestRepository.save(bookRequest);
			}
		}
		
		if (BookRequestEnum.RETURN.toString().equalsIgnoreCase(action) && bookRequest.getStatus().equalsIgnoreCase(BookRequestEnum.ISSUED.toString())) {
				bookRequest.setStatus(action);
				bookreRequestRepository.save(bookRequest);		
		}
		
		if (BookRequestEnum.COMPLETE.toString().equalsIgnoreCase(action) && bookRequest.getStatus().equalsIgnoreCase(BookRequestEnum.RETURN.toString())) {
			int updatedQuantity = book.getQuantity() + bookRequest.getQuantity();
			if (updatedQuantity > 0) {
				book.setQuantity(updatedQuantity);
				bookRepository.save(book);
				bookRequest.setStatus(action);
				bookreRequestRepository.save(bookRequest);
			}
		}
		return bookRequest;
	}



	@Override
	public List<BookRequest> getRequestByUserIdAndStatusAndBookId(String userId, String status, String bookId) {
		List<BookRequest> userIdAndStatusAndBookId = bookreRequestRepository.findByUserIdAndStatusAndBookId(Long.parseLong(userId) , status, Long.parseLong(bookId));
		return userIdAndStatusAndBookId;
	}

	
	
	
}
