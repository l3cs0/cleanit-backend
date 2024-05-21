package com.example.cleanitbackend.Dto;

public class OrderDto {

    private String userId;
    private String[] items;

    public OrderDto() {
    }

    public OrderDto(String userId, String[] items) {
        this.userId = userId;
        this.items = items;
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

