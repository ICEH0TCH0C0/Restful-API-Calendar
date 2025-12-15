package com.kh.Calendar.mapper;

import com.kh.Calendar.controller.dto.UserResponse;
import com.kh.Calendar.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int addUsers(User user);
    User findByIdPwd(User user);
}
