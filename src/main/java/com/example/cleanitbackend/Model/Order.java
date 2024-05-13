package com.example.cleanitbackend.Model;

import java.util.Arrays;

public class Order {
    private long id;
    private String userId;
    private String[] items;

    public Order() {
    }

    public Order(long id, String userId, String[] items) {
        this.id = id;
        this.userId = userId;
        this.items = items;
    }

    public Order(String userId, String[] items) {
        this.userId = userId;
        this.items = items;
    }

    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", items=" + Arrays.toString(items) +
                '}';
    }
}
