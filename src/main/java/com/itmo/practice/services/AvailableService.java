package com.itmo.practice.services;

import com.itmo.practice.model.Available;
import com.itmo.practice.repositories.AvailableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableService {

    @Autowired
    private AvailableRepository availableRepository;

    public List<Available> getList() {
        return availableRepository.findAll();
    }

    public String allAvailabilities() {
        StringBuilder sb = new StringBuilder();
        for (Available available : getList()) {
            sb.append(available.getId()).append("; ");
        }
        return sb.toString();
    }
}