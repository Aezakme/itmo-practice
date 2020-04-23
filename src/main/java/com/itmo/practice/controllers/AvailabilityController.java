package com.itmo.practice.controllers;


import com.itmo.practice.entity.Availability;
import com.itmo.practice.entity.Book;
import com.itmo.practice.services.AvailableService;
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
@RequestMapping("/availability")
public class AvailabilityController {

    @Autowired
    private AvailableService availableService;

    @ApiOperation(value = "Get all available ids", notes = "???")
    @GetMapping(value = "/all")
    public List<Availability> getMainPage() {
        return availableService.getAllAvailabilities();
    }

    @ApiOperation(value = "Get info by book id", notes = "--")
    @GetMapping(value = "/{id}")
    public List<Book> getBookInfo(@PathVariable long id) {
        return availableService.getBooksByOfficeId(id);
    }


    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public void addAvailableBook(@RequestBody Availability availability) {
        availableService.addAvailableBook(availability);
    }
}