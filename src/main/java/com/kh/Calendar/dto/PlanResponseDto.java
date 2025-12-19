package com.kh.Calendar.dto;

import com.kh.Calendar.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanResponseDto {
    private Long planNo;
    private String planTitle;
    private String date;
    private String planContent;
    private Long categoryNo;
    private String categoryName;

    // Entity -> DTO 변환 메소드
    public static PlanResponseDto of(Plan plan) {
        return PlanResponseDto.builder()
                .planNo(plan.getPlanNo())
                .planTitle(plan.getPlanTitle())
                .date(plan.getDate())
                .planContent(plan.getPlanContent())
                .categoryNo(plan.getCategory() != null ? plan.getCategory().getCategoryNo() : null)
                .categoryName(plan.getCategory() != null ? plan.getCategory().getCategoryName() : "미지정")
                .build();
    }
}
