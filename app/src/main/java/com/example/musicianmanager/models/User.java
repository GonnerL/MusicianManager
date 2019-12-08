package com.example.musicianmanager.models;

public class User {
    private String email;
    private String password;
    private String status;
    private String name;

    public User(String email, String password, String status, String name) {
        this.email = email;
        this.password = password;
        this.status = status;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
