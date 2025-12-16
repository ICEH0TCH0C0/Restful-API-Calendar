package com.kh.Calendar.controller.dto.request;

import com.kh.Calendar.entity.User;
import lombok.*;

public class UserRequest {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignupUserDto {
        private String userId;
        private String userPwd;
        private String userName;
        private String userPhone;
        private String userEmail;

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

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginUserDto {
        private String userId;
        private String userPwd;

        public User toEntity() {
            return User.builder()
                    .userId(userId)
                    .userPwd(userPwd)
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateUserDto {
        private Long userNo;      // 누구인지 식별 (필수)
        private String userName;  // 수정할 이름
        private String userPhone; // 수정할 번호
        private String userEmail; // 수정할 이메일


    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FindUserDto {
        private String userId;
        private String userName;
        private String userPhone;
    }
}
