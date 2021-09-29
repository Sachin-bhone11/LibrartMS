package com.iacsd.library.DTO;

import com.iacsd.library.model.Book;
import com.iacsd.library.model.BookRequest;
import com.iacsd.library.model.User;

public class UserBorrowBookDetails {
	
	private BookRequest bookRequest;
	
	private User user;
	
	private Book book;
	
	public UserBorrowBookDetails() {
		
	}

	public UserBorrowBookDetails(BookRequest bookRequest, User user, Book book) {
	//	super();
		this.bookRequest = bookRequest;
		this.user = user;
		this.book = book;
	}

	public BookRequest getBookRequest() {
		return bookRequest;
	}

	public void setBookRequest(BookRequest bookRequest) {
		this.bookRequest = bookRequest;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	
	
	
	
}
