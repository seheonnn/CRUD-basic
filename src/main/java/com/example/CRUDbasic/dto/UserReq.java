package com.example.CRUDbasic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserReq {
//    @NoArgsConstructor
//    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserJoinReq {
        private String email;
        private String name;
        private String password;
    }

    @Getter
    @Setter
    public static class UserUpdateReq {
        private String name;
        private String password;
    }
}
