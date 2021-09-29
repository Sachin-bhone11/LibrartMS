package com.iacsd.library.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.WhereJoinTable;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String userPassword;
	
	@Column
	private String role;
	
	@Column(unique = true)
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "book_request",
	joinColumns = @JoinColumn(name="userId"),
	inverseJoinColumns = @JoinColumn(name="bookId"))
	@WhereJoinTable(clause = "status='ISSUED'")
	@JsonIgnore
	private List<Book> books = new ArrayList<>();
	
	
	public User() {
		
	}


	public User(String firstName, String lastName, String userPassword, String email, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userPassword = userPassword;
		this.email = email;
		this.role = role;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	

	public List<Book> getBooks() {
		return books;
	}


	public void AddBook(Book book) {
		this.books.add(book);
	}


	
	
	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", userPassword=" + userPassword
				+ ", emailId=" + email + "]";
	}
	
	
	
}
