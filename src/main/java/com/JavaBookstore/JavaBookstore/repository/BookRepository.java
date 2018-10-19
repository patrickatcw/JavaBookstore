package com.JavaBookstore.JavaBookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.JavaBookstore.JavaBookstore.domain.Book;

import java.util.List;


public interface BookRepository extends CrudRepository<Book, Long>{

    //List<Object> findById();
    //List<Book> findById();

}