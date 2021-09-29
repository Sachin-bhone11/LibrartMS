package com.iacsd.library.service;

import java.util.List;

import com.iacsd.library.model.Book;
import com.iacsd.library.model.User;

public interface BookService {
	
	public List<Book> findBooks();
	
	public Book saveBook(Book book);
	
	public Book updateBook(Book book);
	
	public void deleteBook(Book book);
	

}
