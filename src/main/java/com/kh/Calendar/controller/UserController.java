package com.kh.Calendar.controller;

import com.kh.Calendar.controller.dto.UserResponse;
import com.kh.Calendar.controller.dto.request.UserRequest;
import com.kh.Calendar.entity.User;
import com.kh.Calendar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public  ResponseEntity<?> findByIdPwd(@RequestBody UserRequest.LoginUserDto request) {
        User user = request.toEntity();
        User result = userService.findByIdPwd(user);
        if(result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("login failed", HttpStatus.NOT_FOUND);
        }
    }
}
