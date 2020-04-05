package com.itmo.practice.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.Set;

@Entity(name = "offices")
public class Office {

    @Id
    Long id;

    String name;
    String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
