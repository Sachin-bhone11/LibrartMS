package com.iacsd.library.service;

import java.util.List;

import com.iacsd.library.model.Book;
import com.iacsd.library.model.BookRequest;

public interface BookRequestService {
	public BookRequest cretaeRequest(BookRequest bookRequest);
	
	public BookRequest updateRequest(BookRequest bookRequest);
	
	public List<BookRequest> getAllRequest();
	
	public List<BookRequest> getRequestByRequestStatus(String status);
	
	public List<BookRequest> getRequestByUserIdAndRequestStatus(String userId, String status);
	
	List<BookRequest> getRequestByUserIdAndStatusAndBookId(String userId, String status, String bookId);
	
	public BookRequest adminActionOnBookRequest(String requestId, String action);

}
