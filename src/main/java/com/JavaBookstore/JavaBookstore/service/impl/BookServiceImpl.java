package com.JavaBookstore.JavaBookstore.service.impl;

import java.util.List;


import com.JavaBookstore.JavaBookstore.domain.Book;
import com.JavaBookstore.JavaBookstore.repository.BookRepository;
import com.JavaBookstore.JavaBookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book findById(Long id) {

        //https://www.concretepage.com/spring-boot/spring-boot-crudrepository-example
        Book book = bookRepository.findById(id).get();
        return book;


    }

}







