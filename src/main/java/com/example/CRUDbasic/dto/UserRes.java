package com.example.CRUDbasic.dto;

import com.example.CRUDbasic.config.RoleType;
import com.example.CRUDbasic.entities.UserEntity;
import lombok.*;

public class UserRes {
    @Getter
    @Setter
    @Builder // Converter 에서 builder 패턴 이용
    @AllArgsConstructor(access = AccessLevel.PROTECTED) // Builder 사용 시 모든 파라미터를 갖는 생성자를 필요로 함
//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserJoinRes {
        private String email;
        private String name;
        private char status;
        private RoleType role;

        public UserJoinRes(UserEntity user) {
            this.email = user.getEmail();
            this.name = user.getName();
            this.status = user.getStatus();
            this.role = user.getRole();
        }
    }
}
