package com.iacsd.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iacsd.library.model.Book;
import com.iacsd.library.model.User;
import com.iacsd.library.repository.BookRepository;
import com.iacsd.library.repository.UserRepository;

@Service
@Transactional
public class BookServicempl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	
	public Book saveBook(Book book) {
		return bookRepository.save(book);
		//return null;
	}

	
	public Book updateBook(Book book) {
		return bookRepository.save(book);
		//return null;
	}

	
	public void deleteBook(Book book) {
		bookRepository.delete(book);
	}


	public List<Book> findBooks() {
		return bookRepository.findAll();
	}





	
	
	
}
