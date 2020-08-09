package com.freetests4u.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.freetests4u.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{

	@Query("FROM Book b WHERE b.title=:title")
	Collection<Book> getBook(@Param("title") String title);
	
	@Query("SELECT distinct(b.category) FROM Book b")
	List<String> getBookCategories();
}
