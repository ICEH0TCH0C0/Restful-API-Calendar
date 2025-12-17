package com.kh.Calendar.service;

import com.kh.Calendar.dto.PlanRequestDto;
import com.kh.Calendar.dto.PlanResponseDto;

import java.util.List;

public interface PlanService {

    // 일정 등록
    PlanResponseDto createPlan(Long userNo, PlanRequestDto requestDto);

    // 일정 수정
    PlanResponseDto updatePlan(Long planNo, PlanRequestDto requestDto);

    // 일정 삭제
    void deletePlan(Long planNo);

    // 일정 검색
    List<PlanResponseDto> searchPlans(Long userNo, String date, String keyword);
}
