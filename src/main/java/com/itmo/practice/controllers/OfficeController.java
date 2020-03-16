package com.itmo.practice.controllers;


import com.itmo.practice.services.BookService;
import com.itmo.practice.services.OfficeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @ApiOperation(value = "Get all offices", notes = "Get names of all the offices in base")
    @GetMapping(value = "/offices")
    public String getMainPage() {
        return officeService.allOffices();
    }
}