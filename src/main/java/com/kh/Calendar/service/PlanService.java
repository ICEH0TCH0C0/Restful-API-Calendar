package com.kh.Calendar.service;

import com.kh.Calendar.controller.dto.request.PlanRequest;
import com.kh.Calendar.controller.dto.response.PlanResponse;

import java.util.List;

public interface PlanService {
    Long addPlan(PlanRequest.AddPlanDto request);
    int updatePlan(PlanRequest.UpdatePlanDto request);
    int deletePlan(Long planNo);
    List<PlanResponse.PlanDto> searchPlans(Long userNo, String date, String keyword);
}