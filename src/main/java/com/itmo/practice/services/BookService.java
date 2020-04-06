package com.itmo.practice.services;

import com.itmo.practice.entity.Book;
import com.itmo.practice.entity.BookInfo;
import com.itmo.practice.repositories.AvailabilityRepository;
import com.itmo.practice.repositories.BookRepository;
import com.itmo.practice.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public String allBooks() {
        StringBuilder sb = new StringBuilder();
        for (Book book : getList()) {
            sb.append(book.getTitle()).append("; ");
        }
        return sb.toString();
    }
    public String getBookInfo(Long id) {
        List<BookInfo> bookInfos = bookRepository.findAvailabilityById(id);
        StringBuilder sb = new StringBuilder();
        boolean bookDataAdded = false;
        for (BookInfo bookInfo : bookInfos) {
            if (!bookDataAdded) {
                bookDataAdded = true;
                sb.append("Title: ")
                        .append(bookInfo.getTitle())
                        .append(", Author: ")
                        .append(bookInfo.getAuthor())
                        .append("<br>");
                sb.append("Offices: ").append( "<br>");
            }

            sb.append("Office name: ")
                    .append(bookInfo.getAddress())
                    .append(", Address: ")
                    .append(bookInfo.getAddress())
                    .append(", Amount: ")
                    .append(bookInfo.getAmount())
                    .append("<br>");
        }
        return sb.toString();
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