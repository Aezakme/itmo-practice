package com.itmo.practice.services;

import com.itmo.practice.entity.Book;
import com.itmo.practice.entity.Office;
import com.itmo.practice.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private AvailableService availableService;

    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }

    public List<Book> getOfficeBooksList(Long id) {
        return availableService.getBooksByOfficeId(id);
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
