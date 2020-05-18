package com.example.demo.response;

import java.util.List;

import com.example.demo.domain.Book;

public class GetBookResponse {
	
	List<GetBookEntity> books;

	public List<GetBookEntity> getList() {
		return books;
	}

	public void setList(List<GetBookEntity> list) {
		this.books = list;
	}
}
