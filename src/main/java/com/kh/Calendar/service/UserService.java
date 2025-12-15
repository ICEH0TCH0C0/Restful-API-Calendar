package com.kh.Calendar.service;

import com.kh.Calendar.controller.dto.UserResponse;
import com.kh.Calendar.entity.User;

import java.util.List;


public interface UserService {
    int addUsers(User user);
    User findByIdPwd(User user);
}
