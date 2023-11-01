package com.example.CRUDbasic.config;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RoleType {

    USER(0, "USER"),
    MANAGER(1, "MANAGER");

    private final Integer value;
    private final String role;


    @JsonValue
    public String getRole() {
        return role;
    }
}
