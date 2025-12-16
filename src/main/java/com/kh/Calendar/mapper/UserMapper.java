package com.kh.Calendar.mapper;

import com.kh.Calendar.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int addUsers(User user);
    User findByIdPwd(User user);

    // 회원 정보 수정
    int updateUser(User user);

    // 수정 후 최신 정보를 다시 가져오기 위해 (PK로 조회)
    User findByUserNo(Long userNo);

    // 아이디 찾기
    String findId(User user);

    // 비밀번호 찾기
    String findPwd(User user);
}
