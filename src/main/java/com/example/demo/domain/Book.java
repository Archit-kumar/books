package com.example.demo.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.request.AddBookRequest;


@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue
	private Long id;

	public Book(AddBookRequest addBookRequest) {

		this.isbn = addBookRequest.getIsbn();
		this.author = addBookRequest.getAuthor();
		this.price = Double.parseDouble(addBookRequest.getPrice());
		this.title = addBookRequest.getTitle();
		this.count = 1;

	}

	public Book() {
		
	}

	@Column(name = "isbn", unique = true)
	private String isbn;

	@Column(name = "title")
	private String title;

	@Column(name = "author")
	private String author;

	@Column(name = "price")
	private Double price;

	@Column(name = "count")
	private Integer count;

	public Long getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}

