package com.kh.Calendar.service;

import com.kh.Calendar.entity.User;


public interface UserService {
    int addUsers(User user);
    User findByIdPwd(User user);
}
