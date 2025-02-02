package com.example.cleanitbackend.Dto;

public class OrderDto {

    private long userId;
    private String notes;
    private String[] items;

    public OrderDto() {
    }

    public OrderDto(long userId, String[] items) {
        this.userId = userId;
        this.items = items;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }
}

