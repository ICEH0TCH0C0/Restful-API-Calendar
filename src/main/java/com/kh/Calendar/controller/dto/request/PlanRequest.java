package com.kh.Calendar.controller.dto.request;

import com.kh.Calendar.entity.Plan;
import com.kh.Calendar.entity.User;
import lombok.*;

public class PlanRequest {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddPlanDto {
        private Long userNo;       // 누구의 일정인지
        private String date;       // 날짜
        private String planTitle;  // 제목
        private String planContent;// 내용

        public Plan toEntity(User user) {
            return Plan.builder()
                    .user(user)
                    .date(date)
                    .planTitle(planTitle)
                    .planContent(planContent)
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdatePlanDto {
        private Long planNo;       // 수정할 일정의 고유 번호 (PK)
        private String planTitle;  // 바꿀 제목
        private String planContent;// 바꿀 내용
    }
}