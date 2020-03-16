package com.itmo.practice.controllers;


import com.itmo.practice.services.AvailableService;
import com.itmo.practice.services.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvailableController {

    @Autowired
    private AvailableService availableService;

    @ApiOperation(value = "Get all available ids", notes = "???")
    @GetMapping(value = "/available")
    public String getMainPage() {
        return availableService.allAvailabilities();
    }


}