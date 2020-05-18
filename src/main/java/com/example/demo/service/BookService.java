package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.request.AddBookRequest;
import com.example.demo.response.AddBookResponse;
import com.example.demo.response.GetBookEntity;
import com.example.demo.response.GetBookResponse;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.example.demo.response.GetMediaCoverageResponse;
import com.example.demo.response.MediaCoverageDTO;

@Service
public class BookService {

	private BookRepository bookRepository;
	private RestTemplate restTemplate;

	Logger log = LogManager.getLogger(BookService.class);
	public BookService(BookRepository bookRepository, @Qualifier("restTemplate")RestTemplate restTemplate) {
		this.bookRepository = bookRepository;
		this.restTemplate =restTemplate;
	}

	public AddBookResponse addBook(AddBookRequest request) {

		Optional<Book> optionalBook = Optional.ofNullable(bookRepository.findByIsbn(request.getIsbn()));
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			book.setCount(book.getCount() + 1);
			bookRepository.save(book);
			return getAddBookResponseFromBook(book);
		} else {
			Book book = constructBookDomain(request);
			book = bookRepository.save(book);
			return getAddBookResponseFromBook(book);
		}

	}

	private AddBookResponse getAddBookResponseFromBook(Book book) {

		AddBookResponse response = new AddBookResponse(book);
		return response;
	}

	private Book constructBookDomain(AddBookRequest request) {

		Book book = new Book(request);
		return book;

	}

	public GetBookResponse getBook(String category, String value) {

		if (category.equalsIgnoreCase("isbn")) {
			Book book = bookRepository.findByIsbn(value);
			GetBookResponse bookResponse = new GetBookResponse();
			List<GetBookEntity> bookEntities = new ArrayList<>();
			bookEntities.add(getBookEntityFromBook(book));
			bookResponse.setList(Collections.unmodifiableList(bookEntities));
			return bookResponse;
		} else if (category.equalsIgnoreCase("title")) {
			List<Book> books = bookRepository.findByTitleContaining(value);
			GetBookResponse bookResponse = new GetBookResponse();
			List<GetBookEntity> bookEntities = new ArrayList<>();
			for (Book b : books) {
				bookEntities.add(getBookEntityFromBook(b));
			}
			bookResponse.setList(Collections.unmodifiableList(bookEntities));
			return bookResponse;
		} else {
			List<Book> books = bookRepository.findByAuthorContaining(value);
			GetBookResponse bookResponse = new GetBookResponse();
			List<GetBookEntity> bookEntities = new ArrayList<>();
			for (Book b : books) {
				bookEntities.add(getBookEntityFromBook(b));
			}
			bookResponse.setList(Collections.unmodifiableList(bookEntities));
			return bookResponse;

		}
	}

	private GetBookEntity getBookEntityFromBook(Book book) {
		GetBookEntity bookEntity = new GetBookEntity(book);
		return bookEntity;
	}

//	@HystrixCommand
	public GetMediaCoverageResponse getMediaCoverage(String isbn) {
		
		Book book = bookRepository.findByIsbn(isbn);
		if(book == null) {
			throw new RuntimeException("Book With ISBN does not exist "+isbn);
		}
		
		List<MediaCoverageDTO> details =getMediaCoverageDetails();
		
		List<String> titles = getBookTitle(book.getTitle(), details);
		
		GetMediaCoverageResponse response = new GetMediaCoverageResponse();
		response.setTitles(Collections.unmodifiableList(titles));

		return response;
		
	}


	private List<String> getBookTitle(String title, List<MediaCoverageDTO> details) {
		return details.stream().filter(o -> (o.getBody().contains(title) || o.getTitle().contains(title)))
				.collect(Collectors.toList()).stream().map(MediaCoverageDTO :: getTitle).collect(Collectors.toList());

	}

	private List<MediaCoverageDTO> getMediaCoverageDetails() {
		String url ="/posts";
		try {
			ResponseEntity<List<MediaCoverageDTO>> responseEntity =restTemplate.exchange(url, HttpMethod.GET , null, new ParameterizedTypeReference<List<MediaCoverageDTO>>() {
			});
			if(responseEntity.hasBody()) {
				return responseEntity.getBody();
			}
		}
		catch (Exception e) {
		 throw new RuntimeException(e);
		}
		return null;
	}

	public String buyBook(String isbn) {
		Optional<Book> optionalBook = Optional.ofNullable(bookRepository.findByIsbn(isbn));
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			if(book.getCount() !=1) {
				book.setCount(book.getCount() - 1);
				bookRepository.save(book);
			}
			return "Book Ordered successfully, thanks for ordering!";
		} else {
			new RuntimeException("Book is Not avaliable with provided isbn "+isbn);
		}
		return null;
	}

	
}
