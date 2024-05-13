package com.example.cleanitbackend.Model;

import java.util.Arrays;

public class Order {
    private long id;
    private String customerId;
    private String[] items;

    public Order() {
    }

    public Order(long id, String customerId, String[] items) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
    }

    public Order(String customerId, String[] items) {
        this.customerId = customerId;
        this.items = items;
    }

    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", items=" + Arrays.toString(items) +
                '}';
    }
}
