package com.JavaBookstore.JavaBookstore.service.impl;


import com.JavaBookstore.JavaBookstore.repository.BookRepository;
import com.JavaBookstore.JavaBookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JavaBookstore.JavaBookstore.domain.Book;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

}