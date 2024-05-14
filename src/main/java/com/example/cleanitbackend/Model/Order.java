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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }
}
