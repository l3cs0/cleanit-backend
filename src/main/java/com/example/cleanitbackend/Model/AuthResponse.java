package com.example.cleanitbackend.Model;


    public class AuthResponse {
        private String message;
        private long userId;
        private String role;
        private String name;
    
        public AuthResponse(String message, String role, String name, long userId) {
            this.message = message;
            this.role = role;
            this.name = name;
            this.userId = userId;
        }

        public AuthResponse(String message, String role, String name) {
            this.message = message;
            this.role = role;
            this.name = name;
        }

        public long getUserId() {
            return userId;
        }
        public void setUserId(long userId) {
            this.userId = userId;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public String getRole() {
            return role;
        }
        public void setRole(String role) {
            this.role = role;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
}
