package com.JavaBookstore.JavaBookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.JavaBookstore.JavaBookstore.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
