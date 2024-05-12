package com.example.cleanitbackend.Order;

import java.time.LocalDate;

public class Customer {
    private long id;
    private String name;
    private String email;
    private LocalDate dob;

    public Customer() {
    }

    public Customer(long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Customer(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }
}
