package com.iacsd.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book_request")
public class BookRequest {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private long userId;
	
	@Column
	private long bookId;
	
	@Column
	private String status;
	
	@Column
	private int quantity;
	
	public BookRequest() {
		
	}


	public BookRequest(long userId, long bookId, String status, int quantity) {
		super();
		this.userId = userId;
		this.bookId = bookId;
		this.status = status;
		this.quantity = quantity;
	}



	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "BookRequest [userId=" + userId + ", bookId=" + bookId + ", status=" + status + "]";
	}
	
	
}
