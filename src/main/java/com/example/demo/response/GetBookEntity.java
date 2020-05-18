package com.example.demo.response;

import com.example.demo.domain.Book;

public class GetBookEntity {


	private String isbn;
	private String author;
	private String title;
	private String price;

	public GetBookEntity(Book book) {
		this.isbn = book.getIsbn();
		this.author = book.getAuthor();
		this.title = book.getTitle();
		this.price = book.getPrice().toString();
		this.price = book.getPrice().toString();
	}

	public GetBookEntity() {
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



}
