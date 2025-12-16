package com.kh.Calendar.controller.dto.response;

import com.kh.Calendar.entity.Plan;
import lombok.Builder;
import lombok.Getter;

public class PlanResponse {
    // (내부에 Plan 정보를 위한 DTO 추가)
    @Getter
    @Builder
    public static class PlanDto {
        private Long id; // 프론트는 id를 씀
        private String title;
        private String content;
        private String date;

        public static PlanDto of(Plan plan) {
            return PlanDto.builder()
                    .id(plan.getPlanNo())
                    .title(plan.getPlanTitle())
                    .content(plan.getPlanContent())
                    .date(plan.getDate())
                    .build();
        }
    }
}
