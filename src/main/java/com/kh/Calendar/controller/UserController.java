package com.kh.Calendar.controller;

import com.kh.Calendar.dto.UserRequestDto;
import com.kh.Calendar.dto.UserResponseDto;
import com.kh.Calendar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = userService.signUp(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> signIn(@RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = userService.signIn(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 회원 정보 수정 (RESTful하게 URL에 userNo를 포함)
    @PatchMapping("/{userNo}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userNo, @RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = userService.updateUser(userNo, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 아이디 찾기
    @PostMapping("/findId")
    public ResponseEntity<Map<String, String>> findId(@RequestBody UserRequestDto requestDto) {
        String foundId = userService.findUserId(requestDto);
        Map<String, String> response = new HashMap<>();
        response.put("userId", foundId);
        return ResponseEntity.ok(response);
    }

    // 비밀번호 찾기
    @PostMapping("/findPwd")
    public ResponseEntity<Map<String, String>> findPwd(@RequestBody UserRequestDto requestDto) {
        String foundPwd = userService.findUserPwd(requestDto);
        Map<String, String> response = new HashMap<>();
        response.put("userPwd", foundPwd);
        return ResponseEntity.ok(response);
    }

    // 회원 탈퇴
    @DeleteMapping("/{userNo}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userNo) {
        userService.deleteUser(userNo);
        return ResponseEntity.ok().build();
    }
}
