package com.example.CRUDbasic.controller;

import com.example.CRUDbasic.dto.UserReq;
import com.example.CRUDbasic.dto.UserRes;
import com.example.CRUDbasic.entities.LogEntity;
import com.example.CRUDbasic.global.BaseException;
import com.example.CRUDbasic.global.BaseResponse;
import com.example.CRUDbasic.service.UserServiceV3;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v3/users") // V3 : Response 객체, Exception Handler 추가
public class UserControllerV3 {
    private final UserServiceV3 userService;
//    @Autowired
//    private UserService userService;


    private final RedisProperties redisProperties;
    // C
    // @ApiOperation 이용하여 Swagger 에 설명을 추가할 수 있음.
    @ApiOperation(value = "회원 가입", notes = "ex)\n\n " +
            "{\n" +
            "    \"email\": \"abc123@naver.com\",\n" +
            "    \"password\": \"abc123\",\n" +
            "    \"name\": \"짱구\"\n" +
            "}")
    @PostMapping("/create")
    public BaseResponse<UserRes.UserJoinRes> create(@RequestBody UserReq.UserJoinReq userJoinReq) throws BaseException {
        log.info("GET /api/v3/users/create 요청처리 시작");
        log.info("{}", new LogEntity(1L, "GET /api/v3/users/create", "요청처리 시작"));
        UserRes.UserJoinRes userJoinRes = userService.create(userJoinReq);
        log.info("GET /api/v3/users/create 요청처리 완료 > 생성된 유저 : {}", userJoinRes);
//        return ResponseEntity.ok("유저 생성 완료.");

        log.info(redisProperties.getHost());
        log.info(String.valueOf(redisProperties.getPort()));

        return new BaseResponse<>(userJoinRes);
    }

    // R
    @ApiOperation(value = "회원 정보 가져오기", notes = "ex)\n\n " +
            " http://localhost:8080/api/v3/users/1 ")
    @GetMapping("/{userId}")
    public BaseResponse<UserRes.UserJoinRes> read(@PathVariable Long userId, HttpServletRequest request) throws Exception {
        log.info("========= 헤더 정보 확인 ==========");
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headers = new HashMap<>();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headers.put(headerName, headerValue);
        }
        System.out.println("Headers received: " + headers);
        log.info("================================");
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
