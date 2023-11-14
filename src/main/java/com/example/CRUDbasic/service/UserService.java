package com.example.CRUDbasic.service;

import com.example.CRUDbasic.config.RoleType;
import com.example.CRUDbasic.dto.UserDTO;
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
public class UserService {
    private final UserRepository userRepository;
//    @Autowired
//    private UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserEntity create(UserDTO user) {
        String encryptedPw = encoder.encode(user.getPassword());
        UserEntity newUser = UserEntity.builder()
                .email(user.getEmail())
                .password(encryptedPw)
                .name(user.getName())
                .status('A')
                .role(RoleType.USER)
                .build();

        return userRepository.save(newUser);
    }

    public UserEntity read(UserDTO user, HttpServletRequest request) throws Exception {
        String email = request.getHeader("email"); // Header 에서 email 이라는 Key 의 Value 를 가져옴. 보통 JWT 토큰 사용
        // 방법 1. email 로 UserEntity 찾기
        log.info("========================================");
        log.info(email);
        log.info(String.valueOf(userRepository.findByEmail(email))); // log.info() 는 @Slf4j 에서 지원. log.info() 안에는 String 만 가능
        UserEntity newUser = userRepository.findByEmail(email).orElseThrow(() -> new Exception("해당 사용자를 찾을 수 없습니다"));
        log.info("========================================");
        // 방법 2. userId 로 UserEntity 찾기
        userRepository.findById(user.getUserId());

        return newUser;
    }

    public UserEntity update(Long userId, UserDTO user) throws Exception {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new Exception("해당 사용자를 찾을 수 없습니다"));
        if (user.getName() != null) {
            userEntity.setName(user.getName());
        }
        if (user.getPassword() != null) {
            String encryptedPw = encoder.encode(user.getPassword());
            userEntity.setPassword(encryptedPw);
        }
        return userRepository.save(userEntity);
    }

    public UserEntity delete1(Long userId) throws Exception {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new Exception("해당 사용자를 찾을 수 없습니다"));
        userEntity.setStatus('D');
        return userRepository.save(userEntity);
    }

    public HttpStatus delete2(Long userId) throws Exception {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new Exception("해당 사용자를 찾을 수 없습니다"));
        userRepository.delete(userEntity);
        return HttpStatus.OK;
    }
}
