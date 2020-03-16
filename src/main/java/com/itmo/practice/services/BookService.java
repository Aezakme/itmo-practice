package com.itmo.practice.services;

import com.itmo.practice.repositories.AvailableRepository;
import com.itmo.practice.repositories.BookInfo;
import com.itmo.practice.model.Book;
import com.itmo.practice.repositories.BookRepository;
import com.itmo.practice.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AvailableRepository availableRepository;
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
        List<BookInfo> bookInfos = bookRepository.getInfoById(id);
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
                    .append(bookInfo.getOffice_name())
                    .append(", Address: ")
                    .append(bookInfo.getOffice_address())
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
        return  bookId;
    }

    public void insertBook(BookInfo bookInfo) {
        Long bookId = bookIdByTitleAndAuthor(bookInfo.getTitle(), bookInfo.getAuthor());
        if (bookId != null) {
            availableRepository.increaseAmount(bookId, bookInfo.getOffice_name(), bookInfo.getAmount());
            return;
        }
        bookId = (long) bookRepository.uniqueBookCount();
        bookRepository.insertBook(bookId, bookInfo.getTitle(), bookInfo.getAuthor());
        Long availabilityId = (long) availableRepository.availabilityCount();
        Long officeId = officeRepository.getOfficeId(bookInfo.getOffice_name());
        availableRepository.insertAvailability(availabilityId, bookId, officeId, bookInfo.getAmount());
    }
}