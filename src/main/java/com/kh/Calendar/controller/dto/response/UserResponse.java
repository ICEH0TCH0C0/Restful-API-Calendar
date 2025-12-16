package com.kh.Calendar.controller.dto.response;

import com.kh.Calendar.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class UserResponse {

    @Getter
    @Setter
    @Builder
    public static class LoginUserDto{
        private Long userNo;
        private String userId;

        public static LoginUserDto of(User user){
            return LoginUserDto.builder()
                    .userNo(user.getUserNo())
                    .userId(user.getUserId())
                    .build();
        }

    }
}