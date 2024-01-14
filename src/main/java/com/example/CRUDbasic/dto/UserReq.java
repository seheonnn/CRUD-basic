package com.example.CRUDbasic.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

public class UserReq {
//    @NoArgsConstructor
//    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserJoinReq {
        private String email;
        @Size(min = 3, max = 12)
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
