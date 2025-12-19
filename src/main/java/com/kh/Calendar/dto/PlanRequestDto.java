package com.kh.Calendar.dto;

import com.kh.Calendar.entity.Plan;
import com.kh.Calendar.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanRequestDto {
    private String planTitle;
    private String date;
    private String planContent;
    private Long categoryNo;

    // DTO -> Entity 변환 메소드 (User 객체를 받아야 함)
    public Plan toEntity(User user) {
        return Plan.builder()
                .user(user)
                .planTitle(planTitle)
                .date(date)
                .planContent(planContent)
                .build();
    }
}
