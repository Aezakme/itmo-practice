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

    @ApiOperation(value = "Get all offices", notes = "Show all offices names")
    @GetMapping(value = "/offices")
    public String getOfficesPage() {
        return officeService.getAllOffices();
    }

    @ApiOperation(value = "Get books by office id", notes = "Show office info books titles available in office by its id")
    @GetMapping(value = "/offices/{id}")
    public String getOfficePage(@PathVariable Long id) {
        return officeService.getOfficeInfo(id) +
                "; Available books: "
                + officeService.getOfficesBooks(id);
    }


}
