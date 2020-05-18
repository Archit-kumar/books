//package com.example.demo.controller;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(BookController.class)
//public class BookControllerTest {
//
//	@Autowired
//	MockMvc mvc;
//	final String BASE_URL ="/v1/books";
//	@Test
//	public void getBookTest() throws Exception {
//		
//		  mvc.perform( MockMvcRequestBuilders
//		      .get(BASE_URL+"/")
//		      .accept(MediaType.APPLICATION_JSON))
//		      .andDo(print())
//		      .andExpect(status().isOk())
//		      .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
//		      .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").isNotEmpty());
//		
//	}
//}
