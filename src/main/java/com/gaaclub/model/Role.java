package com.gaaclub.model;

public enum Role {

    ADMIN("ADMIN"), USER("USER");

 private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
