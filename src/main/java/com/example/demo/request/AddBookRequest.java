package com.example.demo.request;


import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@Validated
public class AddBookRequest {

	//@NotBlank(message = "isbn can not be blank or null")
	@JsonProperty("isbn")
	private String isbn;
	//@NotBlank(message = "author can not be blank or null")
	@JsonProperty("author")
	private String author;
//	@NotBlank(message = "title can not be blank or null")
	@JsonProperty("title")
	String title;
	//@NotBlank(message = "price can not be blank or null")
	@JsonProperty("price")
	String price;

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
