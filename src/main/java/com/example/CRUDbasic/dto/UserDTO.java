package com.example.CRUDbasic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long userId;

    private String email;

    private String password;

    private String name;

    private char status;

    private String role;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

//    public UserEntity toEntity() {
//        return UserEntity.builder()
//                .email(email)
//                .password(password)
//                .name(name)
//                .status(status)
//                .role(role)
//                .created_at(created_at)
//                .updated_at(updated_at)
//                .build();
//    }
}
