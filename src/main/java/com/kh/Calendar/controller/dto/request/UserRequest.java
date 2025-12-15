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
}
