package com.example.cleanitbackend.Model;


    public class AuthResponse {
        private String message;
        private String role;
        private String name;
    
        public AuthResponse(String message, String role, String name) {
            this.message = message;
            this.role = role;
            this.name = name;
        }
}
