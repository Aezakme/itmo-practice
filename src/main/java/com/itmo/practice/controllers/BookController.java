package com.itmo.practice.controllers;


import com.itmo.practice.model.Book;
import com.itmo.practice.repositories.BookInfo;
import com.itmo.practice.repositories.BookRepository;
import com.itmo.practice.services.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "Get all books", notes = "Get titles of all the books in base")
    @GetMapping(value = "/books")
    public String getMainPage() {
        return bookService.allBooks();
    }

    @ApiOperation(value = "Get info by book id", notes = "--")
    @GetMapping(value = "/books{id}")
    public String getBookInfo(@PathVariable long id) {
        return bookService.getBookInfo(id);
    }

    @PostMapping(value = "/addBook", consumes = "application/json", produces = "application/json")
    public void addBook(@RequestBody BookInfo bookInfo) {
        bookService.insertBook(bookInfo);
    }
}

