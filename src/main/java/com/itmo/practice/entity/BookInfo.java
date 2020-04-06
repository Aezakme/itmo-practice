package com.itmo.practice.entity;

public class BookInfo extends Book {

    String name;
    String address;
    Integer amount;

    public BookInfo(String title, String author, String name, String address, Integer amount) {
        this.title = title;
        this.author = author;
        this.name = name;
        this.address = address;
        this.amount = amount;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
