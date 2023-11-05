package com.example.CRUDbasic.service;

import com.example.CRUDbasic.config.RoleType;
import com.example.CRUDbasic.dto.UserReq;
import com.example.CRUDbasic.dto.UserRes;
import com.example.CRUDbasic.entities.UserEntity;
import com.example.CRUDbasic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j // 로그 확인 위한 어노테이션
@Service
@RequiredArgsConstructor
public class UserServiceV2 {
    private final UserRepository userRepository;
//    @Autowired
//    private UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserRes.UserJoinRes create(UserReq.UserJoinReq userJoinReq) {
        String encryptedPw = encoder.encode(userJoinReq.getPassword());
        UserEntity newUser = UserEntity.builder()
                .email(userJoinReq.getEmail())
                .password(encryptedPw)
                .name(userJoinReq.getName())
                .status('A')
                .role(RoleType.USER)
                .build();

        return new UserRes.UserJoinRes(userRepository.saveAndFlush(newUser));
    }

    public UserRes.UserJoinRes read(Long userId, HttpServletRequest request) throws Exception {
        // 방법 1. email header 에서 추출하여 UserEntity 찾기
        String email = request.getHeader("email"); // Header 에서 email 이라는 Key 의 Value 를 가져옴. 보통 JWT 토큰 사용
        UserEntity newUser = userRepository.findByEmail(email).orElseThrow(() -> new Exception("해당 사용자를 찾을 수 없습니다"));
        log.info(email); // 추출한 email 확인

        // 방법 2. userId 로 UserEntity 찾기
//        UserEntity newUser = userRepository.findById(userId).orElseThrow(() -> new Exception("해당 사용자를 찾을 수 없습니다"));

        return new UserRes.UserJoinRes(newUser);
    }

    public UserRes.UserJoinRes update(Long userId, UserReq.UserUpdateReq userUpdateReq) throws Exception {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new Exception("해당 사용자를 찾을 수 없습니다"));
        if (userUpdateReq.getName() != null) {
            userEntity.setName(userUpdateReq.getName());
        }
        if (userUpdateReq.getPassword() != null) {
            String encryptedPw = encoder.encode(userUpdateReq.getPassword());
            userEntity.setPassword(encryptedPw);
        }

        return new UserRes.UserJoinRes(userRepository.saveAndFlush(userEntity));
    }

    public UserEntity delete1(Long userId) throws Exception {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new Exception("해당 사용자를 찾을 수 없습니다"));
        userEntity.setStatus('D');
        return userRepository.saveAndFlush(userEntity);
    }

    public HttpStatus delete2(Long userId) throws Exception {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new Exception("해당 사용자를 찾을 수 없습니다"));
        userRepository.delete(userEntity);
        return HttpStatus.OK;
    }
}
