package com.kh.Calendar.dto;

import com.kh.Calendar.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long userNo;
    private String userId;
    private String userName;
    private String userPhone;
    private String userEmail;
    private List<PlanResponseDto> plans; // Plan 정보도 DTO로 변환하여 포함

    // Entity -> DTO 변환 메소드
    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .userNo(user.getUserNo())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userPhone(user.getUserPhone())
                .userEmail(user.getUserEmail())
                .plans(user.getPlans().stream()
                        .map(PlanResponseDto::from) // Plan도 DTO로 변환
                        .collect(Collectors.toList()))
                .build();
    }
}
