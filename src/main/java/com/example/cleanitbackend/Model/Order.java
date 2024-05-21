package com.example.cleanitbackend.Model;

import java.util.Arrays;

public class Order {

    private long id;
    private long userId;
    private String[] items;

    public Order() {
    }

    public Order(long id, long userId, String[] items) {
        this.id = id;
        this.userId = userId;
        this.items = items;
    }

    public Order(long userId, String[] items) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }
}
