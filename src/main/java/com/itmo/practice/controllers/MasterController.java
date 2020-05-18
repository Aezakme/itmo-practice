package com.itmo.practice.controllers;


import com.itmo.practice.services.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class MasterController {


    @ApiOperation(value = "Get info by book id", notes = "--")
    @GetMapping(value = "/book/{id}", produces = "application/json")
    public String getBookInfo(@PathVariable String id) {
        return "book";
    }


    @ApiOperation(value = "Get main page", notes = "--")
    @GetMapping(value = "/")
    public String getBookInfo() {
        return "main";
    }

}