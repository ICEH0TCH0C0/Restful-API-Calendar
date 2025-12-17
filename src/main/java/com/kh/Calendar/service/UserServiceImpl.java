package com.kh.Calendar.service;

import com.kh.Calendar.dto.UserRequestDto;
import com.kh.Calendar.dto.UserResponseDto;
import com.kh.Calendar.entity.User;
import com.kh.Calendar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // final 필드에 대한 생성자 자동 생성
@Transactional(readOnly = true) // 기본적으로는 읽기 전용 트랜잭션
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional // 쓰기 작업이므로 readOnly=false 적용
    public UserResponseDto signUp(UserRequestDto requestDto) {
        User user = requestDto.toEntity();
        User savedUser = userRepository.save(user);
        return UserResponseDto.from(savedUser);
    }

    @Override
    public UserResponseDto signIn(UserRequestDto requestDto) {
        User user = userRepository.findByUserIdAndUserPwd(requestDto.getUserId(), requestDto.getUserPwd())
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
        return UserResponseDto.from(user);
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long userNo, UserRequestDto requestDto) {
        User user = userRepository.findByUserNo(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));

        // 엔티티에 추가한 update 메소드를 사용하여 상태 변경
        user.update(requestDto.getUserName(), requestDto.getUserPhone(), requestDto.getUserEmail());

        // 트랜잭션이 커밋될 때 변경 감지(Dirty Checking)에 의해 자동으로 UPDATE 쿼리가 실행됨
        return UserResponseDto.from(user);
    }

    @Override
    public String findUserId(UserRequestDto requestDto) {
        return userRepository.findUserId(requestDto.getUserName(), requestDto.getUserPhone())
                .orElseThrow(() -> new IllegalArgumentException("해당 정보와 일치하는 아이디가 없습니다."));
    }

    @Override
    public String findUserPwd(UserRequestDto requestDto) {
        return userRepository.findUserPwd(requestDto.getUserId(), requestDto.getUserName(), requestDto.getUserPhone())
                .orElseThrow(() -> new IllegalArgumentException("해당 정보와 일치하는 비밀번호를 찾을 수 없습니다."));
    }
}
