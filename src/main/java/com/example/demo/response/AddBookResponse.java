package com.example.demo.response;

import com.example.demo.domain.Book;

public class AddBookResponse {

	private String isbn;
	private String author;
	private String title;
	private String price;
	private String id;
	private String count;

	public AddBookResponse(Book book) {
		this.isbn = book.getIsbn();
		this.author = book.getAuthor();
		this.title = book.getTitle();
		this.price = book.getPrice().toString();
		this.id = book.getId().toString();
		this.price = book.getPrice().toString();
		this.count = book.getCount().toString();
	}

	public AddBookResponse() {
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
