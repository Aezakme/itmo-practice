package com.itmo.practice.controllers;

import com.itmo.practice.services.BookService;
import com.itmo.practice.services.OfficeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class OfficeController {
    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping(value = "/offices")
    public String getOfficesPage() {
        return officeService.getAllOffices();
    }


    @GetMapping(value = "/offices/{id}")
    public String getOfficePage(@PathVariable Long id) {
        return officeService.getOfficesBooks(id);
    }


}
