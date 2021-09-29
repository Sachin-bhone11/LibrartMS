package com.iacsd.library.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String name;
	
	@Column
	private int quantity;
	
	@Column
	private String author;
	
	@ManyToMany
	@JsonIgnore
	private List<User> users = new ArrayList<>();
	
	public Book() {
		
	}

	public Book(String name, int quantity, String author) {
	//	super();
		this.name = name;
		this.quantity = quantity;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<User> getUsers() {
		return users;
	}

	public void addUser(User user) {
		this.users.add(user);
	}
	
	
	
	
	
}
