package com.example.CRUDbasic.service;

import com.example.CRUDbasic.config.RoleType;
import com.example.CRUDbasic.converter.UserConverter;
import com.example.CRUDbasic.dto.UserReq;
import com.example.CRUDbasic.dto.UserRes;
import com.example.CRUDbasic.entities.UserEntity;
import com.example.CRUDbasic.global.BaseException;
import com.example.CRUDbasic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.example.CRUDbasic.global.BaseResponseStatus.*;

@Slf4j // 로그 확인 위한 어노테이션
@Service
@RequiredArgsConstructor // V3 : Response 객체, Exception Handler 추가
public class UserServiceV3 {

    // 의존성 주입 방식
    private final UserRepository userRepository; // 생성자 주입 / 순환 참조 방지
//    @Autowired // 필드 주입
//    private UserRepository userRepository;

//    static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z]+)(?=.*\\d)(?=.*[!@#$%^&*]).{8,64}$"; // 8자 이상 64자 이하, 대소문자, 특수문자 각각 한 번씩 포함
    private static final String EMAIL_PATTERN = "^.{5,254}$|^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$\n";

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserRes.UserJoinRes create(UserReq.UserJoinReq userJoinReq) throws BaseException {
        if(!userJoinReq.getEmail().matches(EMAIL_PATTERN))
            throw new BaseException(POST_USERS_INVALID_EMAIL);
//        if(!userJoinReq.getPassword().matches(PASSWORD_PATTERN))
//            throw new BaseException(INVALID_PASSWORD_FORMAT);
        if(userRepository.findByEmail(userJoinReq.getEmail()).isPresent())
            throw new BaseException(POST_USERS_EXISTS_EMAIL);

        UserEntity newUser = UserConverter.toUser(userJoinReq);

        // 성능적인 면에선 큰 차이 X
        // 생성자 방식: 코드가 간결, 필드가 많아지면 복잡해짐
        // Converter: 덜 명시적일 수도, 변환 로직이 한곳에 모여 있어 확장성이 좋음.
        // 
//        return new UserRes.UserJoinRes(userRepository.save(newUser)); // 생성자 이용 DTO 변환
        return UserConverter.toUserDto(userRepository.save(newUser)); // Converter 이용 DTO 변환
    }

    public UserRes.UserJoinRes read(Long userId, HttpServletRequest request) throws Exception {
        // 방법 1. email header 에서 추출하여 UserEntity 찾기
//        String email = request.getHeader("email"); // Header 에서 email 이라는 Key 의 Value 를 가져옴. 보통 JWT 토큰 사용
        String email = request.getHeader("authorization").split(" ")[1];
        UserEntity newUser = userRepository.findByEmail(email).orElseThrow(() -> new BaseException(USERS_EMPTY_USER_ID));
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

        return new UserRes.UserJoinRes(userRepository.save(userEntity));
    }

    public UserRes.UserJoinRes delete1(Long userId) throws Exception {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new Exception("해당 사용자를 찾을 수 없습니다"));
        userEntity.setStatus('D');
        return new UserRes.UserJoinRes(userRepository.save(userEntity));
    }

    public HttpStatus delete2(Long userId) throws Exception {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new Exception("해당 사용자를 찾을 수 없습니다"));
        userRepository.delete(userEntity);
        return HttpStatus.OK;
    }
}
