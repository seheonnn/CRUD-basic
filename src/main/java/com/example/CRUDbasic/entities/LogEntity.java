package com.example.CRUDbasic.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

// 로그 객체
@AllArgsConstructor
public class LogEntity {
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("api")
    private String api;
    @JsonProperty("message")
    private String message;

    // JSON 변환
    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return super.toString();
    }

}
