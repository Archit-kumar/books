package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.AddBookRequest;
import com.example.demo.response.AddBookResponse;
import com.example.demo.response.GetBookResponse;
import com.example.demo.response.GetMediaCoverageResponse;
import com.example.demo.service.BookService;

@RestController
@Validated
@RequestMapping("/v1/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private AddBookResponse addBook( @RequestBody AddBookRequest request) {
		return bookService.addBook(request);
	}
	
	@GetMapping
	private GetBookResponse getBook(@RequestParam(value = "category", required = true) String category,
			@RequestParam(value = "value", required = true) String value) {
		return bookService.getBook(category,value);
	}
	
	@GetMapping("/{isbn}")
	private GetMediaCoverageResponse getMediaCoverage(@PathVariable(name = "isbn") String isbn) {
		 return bookService.getMediaCoverage(isbn);
	}
	
	@GetMapping("/buy/{isbn}")
	private String buyBook(@PathVariable(name = "isbn") String isbn) {
		 return bookService.buyBook(isbn);
	}

}
