package com.itmo.practice.services;

import com.itmo.practice.entity.Book;
import com.itmo.practice.repositories.AvailabilityRepository;
import com.itmo.practice.repositories.BookRepository;
import com.itmo.practice.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AvailabilityRepository availabilityRepository;
    @Autowired
    private OfficeRepository officeRepository;

    public List<Book> getList() {
        return bookRepository.findAll();
    }

    public List<Book> allBooks() {

        return getList();
    }

    public Book getBookInfo(Long id) {
        Optional<Book> bookInfos = bookRepository.findById(id);
        return bookInfos.orElse(null);
    }

    public Long bookIdByTitleAndAuthor(String title, String author) {
        List<Book> books = getList();
        Long bookId = null;
        for (Book book : books) {
            if ((book.getTitle().equals(title)) && (book.getAuthor().equals(author))) {
                bookId = book.getId();
            }
        }
        return bookId;
    }

    public void insertBook(Book book) {
        List<Book> books = bookRepository.findBooksByTitleAndAuthor(book.getTitle(), book.getAuthor());
        if (!books.isEmpty()) {
            return;
        }
        if (book.getYear() == null) {
            book.setYear(new Date(0));
        }

        bookRepository.insertBook(book.getTitle(), book.getAuthor(), book.getDescription(), book.getYear());
    }
}