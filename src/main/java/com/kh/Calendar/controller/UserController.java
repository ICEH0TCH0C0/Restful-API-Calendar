package com.kh.Calendar.controller;

import com.kh.Calendar.dto.TokenDto;
import com.kh.Calendar.dto.UserRequestDto;
import com.kh.Calendar.dto.UserResponseDto;
import com.kh.Calendar.service.UserService;
import jakarta.validation.Valid;
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
    // POST /api/users
    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = userService.signUp(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 로그인 (토큰 발급)
    // POST /api/tokens
    @PostMapping("/tokens")
    public ResponseEntity<TokenDto> signIn(@RequestBody UserRequestDto requestDto) {
        TokenDto tokenDto = userService.signIn(requestDto);
        return ResponseEntity.ok(tokenDto);
    }

    // 아이디 중복 체크
    // GET /api/users/availability?userId=...
    @GetMapping("/users/availability")
    public ResponseEntity<Map<String, Boolean>> checkIdDuplicate(@RequestParam String userId) {
        boolean isDuplicate = userService.checkIdDuplicate(userId);
        
        Map<String, Boolean> response = new HashMap<>();
        response.put("isAvailable", !isDuplicate);

        return ResponseEntity.ok(response);
    }

    // 회원 정보 수정
    // PATCH /api/users/{userNo}
    @PatchMapping("/users/{userNo}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userNo, @RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = userService.updateUser(userNo, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 아이디 찾기
    // POST /api/users/id-recovery
    @PostMapping("/users/id-recovery")
    public ResponseEntity<Map<String, String>> findId(@RequestBody UserRequestDto requestDto) {
        String foundId = userService.findUserId(requestDto);
        Map<String, String> response = new HashMap<>();
        response.put("userId", foundId);
        return ResponseEntity.ok(response);
    }

    // 비밀번호 재설정 (기존 비밀번호 찾기 대체)
    // POST /api/users/password-reset
    @PostMapping("/users/password-reset")
    public ResponseEntity<Map<String, String>> resetPwd(@RequestBody UserRequestDto requestDto) {
        userService.resetUserPwd(requestDto);
        return ResponseEntity.ok(Collections.singletonMap("message", "비밀번호가 성공적으로 변경되었습니다."));
    }

    // 회원 탈퇴
    // DELETE /api/users/{userNo}
    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userNo) {
        userService.deleteUser(userNo);
        return ResponseEntity.noContent().build();
    }
}
