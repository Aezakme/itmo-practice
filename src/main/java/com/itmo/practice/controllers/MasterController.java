package com.itmo.practice.controllers;


import com.itmo.practice.services.BookService;
import com.itmo.practice.services.MasterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterController {

    private final MasterService masterService;
    @Autowired
    private BookService bookService;

    @Autowired
    public MasterController(MasterService masterService) {
        this.masterService = masterService;
    }

    @ApiOperation(value = "----", notes = "---")
    @GetMapping(value = "/")
    public String getMainPage() {
        return bookService.allBooks();
    }

    @ApiOperation(value = "----", notes = "---")
    @GetMapping(value = "/data")
    public String getDataByKey(@RequestParam("key") String key) {
        return masterService.getByKey(key);
    }


    @ApiOperation(value = "----", notes = "---")
    @PutMapping(value = "/data")
    public String putData(@RequestParam("key") String key, @RequestParam("value") String value) {
        return masterService.put(key, value);
    }

}
