package com.kh.Calendar.controller;

import com.kh.Calendar.dto.UserRequestDto;
import com.kh.Calendar.dto.UserResponseDto;
import com.kh.Calendar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // 회원 가입
    @PostMapping("/users")
    public ResponseEntity<?> signUp(@RequestBody UserRequestDto requestDto) {
        try {
            UserResponseDto responseDto = userService.signUp(requestDto);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorBody = Collections.singletonMap("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorBody);
        }
    }

    // 아이디 중복 체크
    @GetMapping("/users/check")
    public ResponseEntity<Map<String, Boolean>> checkIdDuplicate(@RequestParam String userId) {
        boolean isDuplicate = userService.checkIdDuplicate(userId);
        // 사용 가능하면 true, 중복이면 false를 반환하도록 로직 구성 (클라이언트 요청에 맞춤)
        // 클라이언트 코드: if (response.ok) { const isAvailable = await response.json(); return isAvailable; }
        // 따라서 중복이 아니어야(false) 사용 가능(true)

        Map<String, Boolean> response = new HashMap<>();
        response.put("isAvailable", !isDuplicate);

        return ResponseEntity.ok(response);
    }

    // 로그인
    @PostMapping("/sessions")
    public ResponseEntity<UserResponseDto> signIn(@RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = userService.signIn(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 회원 정보 수정 (RESTful하게 URL에 userNo를 포함)
    @PatchMapping("/users/{userNo}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userNo, @RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = userService.updateUser(userNo, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 아이디 찾기
    @PostMapping("/users/id")
    public ResponseEntity<Map<String, String>> findId(@RequestBody UserRequestDto requestDto) {
        String foundId = userService.findUserId(requestDto);
        Map<String, String> response = new HashMap<>();
        response.put("userId", foundId);
        return ResponseEntity.ok(response);
    }

    // 비밀번호 찾기
    @PostMapping("/users/pwd")
    public ResponseEntity<Map<String, String>> findPwd(@RequestBody UserRequestDto requestDto) {
        String foundPwd = userService.findUserPwd(requestDto);
        Map<String, String> response = new HashMap<>();
        response.put("userPwd", foundPwd);
        return ResponseEntity.ok(response);
    }

    // 회원 탈퇴
    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userNo) {
        userService.deleteUser(userNo);
        return ResponseEntity.ok().build();
    }
}
