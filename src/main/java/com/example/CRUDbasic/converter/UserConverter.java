package com.example.CRUDbasic.converter;

import com.example.CRUDbasic.config.RoleType;
import com.example.CRUDbasic.dto.UserReq;
import com.example.CRUDbasic.dto.UserRes;
import com.example.CRUDbasic.entities.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserConverter {

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static UserEntity toUser(UserReq.UserJoinReq userJoinReq) {
        String encryptedPw = encoder.encode(userJoinReq.getPassword());
        return UserEntity.builder()
                .email(userJoinReq.getEmail())
                .password(encryptedPw)
                .name(userJoinReq.getName())
//                .status('A') // @ColumnDefault 이용
                .role(RoleType.USER)
                .build();
    }
    public static UserRes.UserJoinRes toUserDto(UserEntity user){

        // builder 패턴 이용
        return UserRes.UserJoinRes.builder()
                .email(user.getEmail())
                .name(user.getName())
                .status(user.getStatus())
                .role(user.getRole())
                .build();
    }
}
