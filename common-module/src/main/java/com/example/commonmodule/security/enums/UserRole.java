package com.example.commonmodule.security.enums;

public enum UserRole {
    ADMIN("ADMIN"), USER("USER");

    private String value;

    UserRole(String admin) {
        this.value = admin;
    }
}
