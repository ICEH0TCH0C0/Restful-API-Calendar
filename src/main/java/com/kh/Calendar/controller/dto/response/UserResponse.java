package com.kh.Calendar.controller.dto.response;

import com.kh.Calendar.entity.Plan;
import com.kh.Calendar.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;


public class UserResponse {

    @Getter
    @Setter
    @Builder
    public static class LoginUserDto {
        private Long userNo;
        private String userId;
        private String userName;
        private String userPhone;
        private String userEmail;
        private List<PlanResponse.PlanDto> userPlan;

        public static LoginUserDto of(User user){
            return LoginUserDto.builder()
                    .userNo(user.getUserNo())
                    .userId(user.getUserId())
                    .userName(user.getUserName())
                    .userPhone(user.getUserPhone())
                    .userEmail(user.getUserEmail())
                    .userPlan(
                            user.getPlans()
                                    .stream()
                                    .map(PlanResponse.PlanDto::of)
                                    .collect(Collectors.toList()))
                    .build();
        }
    }
}