package com.example.CRUDbasic.controller;

import com.example.CRUDbasic.dto.UserDTO;
import com.example.CRUDbasic.entities.UserEntity;
import com.example.CRUDbasic.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    // C
    @PostMapping("/create")
    public ResponseEntity<UserEntity> create(@RequestBody UserDTO user) {
        log.info("dasdsadas");
        return ResponseEntity.ok(userService.create(user));
    }

    // R
    @GetMapping("")
    public ResponseEntity<Optional<UserEntity>> read(@RequestBody UserDTO user, HttpServletRequest request) {
        return ResponseEntity.ok(userService.read(user, request));
    }

    // U
    @PostMapping("/update")
    public ResponseEntity<UserEntity> update(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userService.update(user));
    }

    // D - status 변경 방식. 회원 정보 유지한 상태로 재가입 가능.
    // but, 회원 정보를 사용하는 다른 기능들에서 해당 회원의 status 가 활성 상태인지 확인하는 로직 필요
    @PostMapping("/delete1")
    public ResponseEntity<UserEntity> delete1(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userService.delete1(user));
    }

    // D - DB 에서 아예 삭제
    @DeleteMapping("/delete2")
    public ResponseEntity<HttpStatus> delete2(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userService.delete2(user));
    }

}
