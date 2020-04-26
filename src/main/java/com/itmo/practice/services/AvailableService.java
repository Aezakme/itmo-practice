package com.itmo.practice.services;

import com.itmo.practice.entity.Availability;
import com.itmo.practice.entity.Book;
import com.itmo.practice.repositories.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    public List<Availability> getAllAvailabilities() {
        return availabilityRepository.findAll();
    }

    public void addAvailableBook(Availability availability) {
        Availability av = availabilityRepository.findByBookIdAndOfficeId(availability.getBook_id(), availability.getOffice_id());
        if (av != null) {
            av.setAmount(av.getAmount() + availability.getAmount());
            availabilityRepository.save(av);
        } else {
            availabilityRepository.insertAvailability(availability.getBook_id(), availability.getOffice_id(), availability.getAmount());
        }
    }

    public List<Book> getBooksByOfficeId(Long id) {
        return availabilityRepository.booksByOfficeId(id);
    }
}