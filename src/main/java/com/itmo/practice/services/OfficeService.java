package com.itmo.practice.services;

import com.itmo.practice.model.Available;
import com.itmo.practice.model.Office;
import com.itmo.practice.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {

    @Autowired
    private OfficeRepository bookRepository;

    public List<Office> getList() {
        return bookRepository.findAll();
    }

    public String allOffices() {
        StringBuilder sb = new StringBuilder();
        for (Office office: getList()) {
            sb.append(office.getName()).append("; ");
        }
        return sb.toString();
    }
}