package com.kh.Calendar.mapper;

import com.kh.Calendar.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int addUsers(User user);
    User findByIdPwd(User user);
}
