package com.kh.Calendar.service;

import com.kh.Calendar.controller.dto.request.UserRequest;
import com.kh.Calendar.entity.User;
import com.kh.Calendar.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public int addUsers(User user) {
        return userMapper.addUsers(user);
    }

    @Override
    public User findByIdPwd(User user) {
        return userMapper.findByIdPwd(user);
    }

    @Override
    public User updateUser(UserRequest.UpdateUserDto request) {

        // [이동됨] 컨트롤러에 있던 빌더 로직을 서비스로 가져옴
        User userToUpdate = User.builder()
                .userNo(request.getUserNo())
                .userName(request.getUserName())
                .userPhone(request.getUserPhone())
                .userEmail(request.getUserEmail())
                .build();

        // 1. 업데이트 실행
        int result = userMapper.updateUser(userToUpdate);

        // 2. 성공했다면, 변경된 최신 정보를 DB에서 다시 조회해서 반환
        if (result > 0) {
            return userMapper.findByUserNo(request.getUserNo());
        }
        return null;
    }

    @Override
    public String findId(UserRequest.FindUserDto request) {
        // DTO -> Entity 변환 (검색 조건만 담음)
        User user = User.builder()
                .userName(request.getUserName())
                .userPhone(request.getUserPhone())
                .build();
        return userMapper.findId(user);
    }

    @Override
    public String findPwd(UserRequest.FindUserDto request) {
        // DTO -> Entity 변환
        User user = User.builder()
                .userId(request.getUserId())
                .userName(request.getUserName())
                .userPhone(request.getUserPhone())
                .build();
        return userMapper.findPwd(user);
    }
}
