package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.demo.request.AddBookRequest;
import com.example.demo.response.GetBookEntity;
import com.example.demo.response.GetBookResponse;
import com.example.demo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class BooksApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private BookService bookService;
	private final String BASE_URL = "/v1/books";

	@Test
	void getBook_Test() throws Exception {
		doReturn(getGetBookResponse()).when(bookService).getBook("title", "title");
		mvc.perform(get(BASE_URL + "?category=title&value=title")).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.list").isArray()).andExpect(jsonPath("$.list.length()").value(1));
	}
	
	@Test
	void addBook_Test() throws Exception {
		mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(getAddBookRequest()))).andDo(print())
				.andExpect(status().isCreated());
	}
	
	@Test
	void buyBook_success() throws Exception{

		MvcResult result = mvc.perform(get(BASE_URL + "/buy/TESTisbn")).andDo(print()).andExpect(status().isOk())
		.andReturn();
		assertEquals(result.getResponse().getContentAsString(), "");
	}


	private GetBookResponse getGetBookResponse() {
		GetBookResponse bookResponse = new GetBookResponse();
		List<GetBookEntity> bookEntities = new ArrayList<GetBookEntity>();
		bookEntities.add(getGetBookEntity());
		bookResponse.setList(bookEntities);
		return bookResponse;
	}

	private GetBookEntity getGetBookEntity() {
		GetBookEntity bookEntity = new GetBookEntity();
		bookEntity.setAuthor("TestAuthor");
		bookEntity.setIsbn("isbn");
		bookEntity.setPrice("price");
		bookEntity.setTitle("title");
		return bookEntity;
	}

	private AddBookRequest getAddBookRequest() {
		AddBookRequest addBookRequest = new AddBookRequest();
		addBookRequest.setAuthor("TESTauthor");
		addBookRequest.setIsbn("TESTisbn");
		addBookRequest.setPrice("20");
		addBookRequest.setTitle("TESTtitle");
		return addBookRequest;
	}

}
