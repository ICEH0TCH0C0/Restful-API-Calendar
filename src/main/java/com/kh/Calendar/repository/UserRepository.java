package com.kh.Calendar.repository;

import com.kh.Calendar.entity.User;
import java.util.Optional;

public interface UserRepository {
    
    // 회원 가입
    User save(User user);
    
    // PK로 회원 조회
    Optional<User> findByUserNo(Long userNo);

    // 아이디로 회원 조회 (중복 체크용, 로그인 시 사용자 정보 로드용)
    Optional<User> findByUserId(String userId);
    
    // 아이디 찾기
    Optional<String> findUserId(String userName, String userPhone);
    
    // 비밀번호 재설정을 위한 사용자 조회 (아이디, 이름, 전화번호 일치 여부 확인)
    Optional<User> findByUserIdAndUserNameAndUserPhone(String userId, String userName, String userPhone);

    // 회원 탈퇴
    void delete(User user);
}
