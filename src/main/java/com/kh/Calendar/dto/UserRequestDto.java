package com.kh.Calendar.dto;

import com.kh.Calendar.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String userId;
    private String userPwd;
    private String userName;
    private String userPhone;
    private String userEmail;

    // DTO -> Entity 변환 메소드
    public User toEntity() {
        return User.builder()
                .userId(userId)
                .userPwd(userPwd)
                .userName(userName)
                .userPhone(userPhone)
                .userEmail(userEmail)
                .build();
    }
}
