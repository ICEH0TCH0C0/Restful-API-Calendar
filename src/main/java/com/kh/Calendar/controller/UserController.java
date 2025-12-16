package com.kh.Calendar.controller;

import com.kh.Calendar.controller.dto.response.UserResponse;
import com.kh.Calendar.controller.dto.request.UserRequest;
import com.kh.Calendar.entity.User;
import com.kh.Calendar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<String> addUsers(@RequestBody UserRequest.SignupUserDto request) {
        User user = request.toEntity();
        int result = userService.addUsers(user);
        if(result > 0) {
            return new ResponseEntity<>("sign-up success", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("sign-up failed", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> findByIdPwd(@RequestBody UserRequest.LoginUserDto request) {
        User user = request.toEntity();
        User result = userService.findByIdPwd(user);
        if(result != null) {
            UserResponse.LoginUserDto responseDto = UserResponse.LoginUserDto.of(result);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("login failed", HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/update")
    @ResponseBody
    public ResponseEntity<?> updateUser(@RequestBody UserRequest.UpdateUserDto request) {

        // [수정] 컨트롤러는 변환 과정 없이 DTO를 그대로 서비스에 토스!
        User updatedUser = userService.updateUser(request);

        if (updatedUser != null) {
            UserResponse.LoginUserDto responseDto = UserResponse.LoginUserDto.of(updatedUser);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Update failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //아이디 찾기
    @PostMapping("/findId")
    public ResponseEntity<?> findId(@RequestBody UserRequest.FindUserDto request) {
        String foundId = userService.findId(request);

        if (foundId != null) {
            // 프론트엔드가 { "userId": "..." } 형태를 기대하므로 Map 사용
            Map<String, String> response = new HashMap<>();
            response.put("userId", foundId);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // 비밀번호 찾기
    @PostMapping("/findPwd")
    public ResponseEntity<?> findPwd(@RequestBody UserRequest.FindUserDto request) {
        String foundPwd = userService.findPwd(request);

        if (foundPwd != null) {
            // 프론트엔드가 { "userPwd": "..." } 형태를 기대하므로 Map 사용
            Map<String, String> response = new HashMap<>();
            response.put("userPwd", foundPwd);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
