package com.itmo.practice.services;

import com.itmo.practice.model.Book;
import com.itmo.practice.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getList() {
        return bookRepository.findAll();
    }

    public String allBooks() {
        String result = "";
        for (Book book : getList()) {
            result += book.getTitle() + "; ";
        }
        return result;
    }
}