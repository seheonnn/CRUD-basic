package com.example.CRUDbasic.dto;

import com.example.CRUDbasic.config.RoleType;
import com.example.CRUDbasic.entities.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class UserRes {
    @Getter
    @Setter
    public static class UserJoinRes {
        private String email;
        private String name;
        private char status;

        public UserJoinRes(UserEntity user) {
            this.email = user.getEmail();
            this.name = user.getName();
            this.status = user.getStatus();
        }
    }
}
