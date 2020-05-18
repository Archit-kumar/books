package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Book;

@Transactional
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	public Book findByIsbn(String isbn);
	
	public List<Book> findByTitleContaining(String title);
	
	public List<Book> findByAuthorContaining(String author);
}
