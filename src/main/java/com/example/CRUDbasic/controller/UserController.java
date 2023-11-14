package com.example.CRUDbasic.controller;

import com.example.CRUDbasic.dto.UserDTO;
import com.example.CRUDbasic.entities.LogEntity;
import com.example.CRUDbasic.entities.UserEntity;
import com.example.CRUDbasic.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {
    private final UserService userService;
//    @Autowired
//    private UserService userService;

    // C
    @PostMapping("/create")
    public ResponseEntity<UserEntity> create(@RequestBody UserDTO user) {
        log.info("GET /api/v1/users/create 요청처리 시작");
        log.info("{}", new LogEntity(1L, "GET /api/v1/users/create", "요청처리 시작"));
        UserEntity newUser = null;
        try {
            newUser = userService.create(user);
        } catch (Exception e) {
            log.error("에러 발생" + e);
        }
        log.info("GET /api/v1/users/create 요청처리 완료 > 생성된 유저 : {}", newUser);
//        return ResponseEntity.ok("유저 생성 완료.");
        return ResponseEntity.ok(newUser);
    }

    // R
    @GetMapping("")
    public ResponseEntity<UserEntity> read(@RequestBody UserDTO user, HttpServletRequest request) throws Exception {
        return ResponseEntity.ok(userService.read(user, request));
    }

    // U
    @PostMapping("/{userId}/update")
    public ResponseEntity<UserEntity> update(@PathVariable Long userId, @RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userService.update(userId, user));
    }

    // D - status 변경 방식. 회원 정보 유지한 상태로 재가입 가능.
    // but, 회원 정보를 사용하는 다른 기능들에서 해당 회원의 status 가 활성 상태인지 확인하는 로직 필요
    @PostMapping("/{userId}/delete1")
    public ResponseEntity<UserEntity> delete1(@PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(userService.delete1(userId));
    }

    // D - DB 에서 아예 삭제
    @DeleteMapping("/{userId}/delete2")
    public ResponseEntity<HttpStatus> delete2(@PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(userService.delete2(userId));
    }

}
