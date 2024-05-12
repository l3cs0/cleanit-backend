package com.example.cleanitbackend.Order;

public class Item {
    private long id;
    private String name;
    private String color;

    public Item() {
    }

    public Item(long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Item(String name, String color) {
        this.name = name;
        this.color = color;
    }
}
