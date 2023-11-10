package com.example.CRUDbasic.controller;

import com.example.CRUDbasic.dto.UserReq;
import com.example.CRUDbasic.dto.UserRes;
import com.example.CRUDbasic.entities.LogEntity;
import com.example.CRUDbasic.global.BaseException;
import com.example.CRUDbasic.global.BaseResponse;
import com.example.CRUDbasic.service.UserServiceV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v3/users") // V3 : Response 객체, Exception Handler 추가
public class UserControllerV3 {
    private final UserServiceV3 userService;
//    @Autowired
//    private UserService userService;

    // C
    @PostMapping("/create")
    public BaseResponse<UserRes.UserJoinRes> create(@RequestBody UserReq.UserJoinReq userJoinReq) throws BaseException {
        log.info("GET /api/v3/users/create 요청처리 시작");
        log.info("{}", new LogEntity(1L, "GET /api/v1/users/create", "요청처리 시작"));
        UserRes.UserJoinRes userJoinRes = userService.create(userJoinReq);
        log.info("GET /api/v3/users/create 요청처리 완료 > 생성된 유저 : {}", userJoinRes);
//        return ResponseEntity.ok("유저 생성 완료.");
        return new BaseResponse<>(userJoinRes);
    }

    // R
    @GetMapping("/{userId}")
    public BaseResponse<UserRes.UserJoinRes> read(@PathVariable Long userId, HttpServletRequest request) throws Exception {
        return new BaseResponse<>(userService.read(userId, request));
    }

    // U
    @PostMapping("/{userId}/update")
    public BaseResponse<UserRes.UserJoinRes> update(@PathVariable Long userId, @RequestBody UserReq.UserUpdateReq userUpdateReq) throws Exception {
        return new BaseResponse<>(userService.update(userId, userUpdateReq));
    }

    // D - status 변경 방식. 회원 정보 유지한 상태로 재가입 가능.
    // but, 회원 정보를 사용하는 다른 기능들에서 해당 회원의 status 가 활성 상태인지 확인하는 로직 필요
    @PostMapping("/{userId}/delete1")
    public BaseResponse<UserRes.UserJoinRes> delete1(@PathVariable Long userId) throws Exception {
        return new BaseResponse<>(userService.delete1(userId));
    }

    // D - DB 에서 아예 삭제
    @DeleteMapping("/{userId}/delete2")
    public BaseResponse<HttpStatus> delete2(@PathVariable Long userId) throws Exception {
        return new BaseResponse<>(userService.delete2(userId));
    }
}
