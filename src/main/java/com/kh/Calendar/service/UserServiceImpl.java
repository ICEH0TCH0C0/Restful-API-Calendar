package com.kh.Calendar.service;

import com.kh.Calendar.controller.dto.UserResponse;
import com.kh.Calendar.entity.User;
import com.kh.Calendar.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
