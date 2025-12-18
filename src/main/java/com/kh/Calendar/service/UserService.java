package com.kh.Calendar.service;

import com.kh.Calendar.dto.UserRequestDto;
import com.kh.Calendar.dto.UserResponseDto;

public interface UserService {

    // 회원 가입
    UserResponseDto signUp(UserRequestDto requestDto);

    // 로그인
    UserResponseDto signIn(UserRequestDto requestDto);

    // 회원 정보 수정
    UserResponseDto updateUser(Long userNo, UserRequestDto requestDto);

    // 아이디 찾기
    String findUserId(UserRequestDto requestDto);

    // 비밀번호 찾기
    String findUserPwd(UserRequestDto requestDto);

    // 회원 탈퇴
    void deleteUser(Long userNo);
}
