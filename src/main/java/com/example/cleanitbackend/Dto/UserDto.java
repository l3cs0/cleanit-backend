package com.example.cleanitbackend.Dto;

public class UserDto {

    private String email;
    private String name;
    private String password;

    public UserDto(String email, String name, String password, String role) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
