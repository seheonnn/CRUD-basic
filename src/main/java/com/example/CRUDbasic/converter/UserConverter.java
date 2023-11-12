package com.example.CRUDbasic.converter;

import com.example.CRUDbasic.dto.UserRes;
import com.example.CRUDbasic.entities.UserEntity;

public class UserConverter {
    public static UserRes.UserJoinRes toUserDto(UserEntity user){

        // builder 패턴 이용
        return UserRes.UserJoinRes.builder()
                .email(user.getEmail())
                .name(user.getName())
                .status(user.getStatus())
                .build();
    }
}
