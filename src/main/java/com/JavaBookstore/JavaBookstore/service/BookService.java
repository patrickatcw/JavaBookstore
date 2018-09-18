package com.JavaBookstore.JavaBookstore.service;

import com.JavaBookstore.JavaBookstore.domain.Book;


import java.util.List;



public interface BookService {

    List<Book> findAll();

    Book findById(Long id);

}
