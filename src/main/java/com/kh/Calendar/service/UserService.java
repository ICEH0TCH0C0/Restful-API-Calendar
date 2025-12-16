package com.kh.Calendar.service;

import com.kh.Calendar.controller.dto.request.UserRequest;
import com.kh.Calendar.entity.User;


public interface UserService {
    int addUsers(User user);
    User findByIdPwd(User user);
    User updateUser(UserRequest.UpdateUserDto request);
    String findId(UserRequest.FindUserDto request);
    String findPwd(UserRequest.FindUserDto request);
}
