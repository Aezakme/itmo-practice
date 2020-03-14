package com.itmo.practice.services;

import com.itmo.practice.model.Book;
import com.itmo.practice.model.Office;
import com.itmo.practice.repositories.BookRepository;
import com.itmo.practice.repositories.OfficeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeService {
    private final OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository, BookRepository bookRepository) {
        this.officeRepository = officeRepository;
    }

    public List<Office> getAllOfficesList() {
        return officeRepository.findAll();
    }

    public String getAllOffices() {
        StringBuilder result = new StringBuilder();
        for (Office office : getAllOfficesList()) {
            result.append(office.getName()).append("; ");
        }
        return result.toString();
    }

    public List<Book> getOfficeBooksList(Long id) {
        return officeRepository.booksByOfficeId(id);
    }

    public String getOfficesBooks(Long id) {
        StringBuilder result = new StringBuilder();
        for (Book b : getOfficeBooksList(id)) {
            result.append(b.getTitle()).append(", ");
        }
        return result.toString();
    }

    public String getOfficeInfo(Long id) {
        Optional<Office> office = officeRepository.findById(id);
        return (office.map(value -> value.getName() + ", " +  value.getAddress()).orElse("No such office"));
    }

}
