package com.itmo.practice.controllers;

import com.itmo.practice.entity.Office;
import com.itmo.practice.services.OfficeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/office")
public class OfficeController {

    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @ApiOperation(value = "Get all offices", notes = "Show all offices names")
    @GetMapping(value = "/all")
    public List<Office> getAllOffices() {
        return officeService.getAllOffices();
    }

    @ApiOperation(value = "Get books by office id", notes = "Show office info books titles available in office by its id")
    @GetMapping(value = "/{id}")
    public String getOfficePage(@PathVariable Long id) {
        return officeService.getOfficeInfo(id) +
                "; Available books: "
                + officeService.getOfficesBooks(id);
    }
}
