package com.kh.Calendar.repository;

import com.kh.Calendar.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    
    // 회원 가입
    User save(User user);
    
    // 로그인 (아이디, 비밀번호로 조회)
    Optional<User> findByUserIdAndUserPwd(String userId, String userPwd);
    
    // 회원 정보 수정 (JPA는 변경 감지를 사용하므로 별도 update 메소드가 필요 없을 수 있지만, 명시적으로 둠)
    // 또는 단순히 조회 후 엔티티 수정만 하면 됨. 여기서는 조회용 메소드 활용
    
    // PK로 회원 조회
    Optional<User> findByUserNo(Long userNo);
    
    // 아이디 찾기
    Optional<String> findUserId(String userName, String userPhone);
    
    // 비밀번호 찾기
    Optional<String> findUserPwd(String userId, String userName, String userPhone);

    // 회원 탈퇴
    void delete(User user);
}
