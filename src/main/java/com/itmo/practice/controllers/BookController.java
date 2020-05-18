package com.itmo.practice.controllers;


import com.itmo.practice.entity.Book;
import com.itmo.practice.services.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "Get all books", notes = "Get titles of all the books in base")
    @GetMapping(value = "/all", produces = "application/json")
    public List<Book> getMainPage() {
        return bookService.allBooks();
    }

    @ApiOperation(value = "Get info by book id", notes = "--")
    @GetMapping(value = "/value/{id}", produces = "application/json")
    public Book getBookInfo(@PathVariable long id) {
        return bookService.getBookInfo(id);
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public void addBook(@RequestBody Book book) {
        bookService.insertBook(book);
    }
}

