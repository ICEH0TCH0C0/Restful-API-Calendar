package com.kh.Calendar.service;

import com.kh.Calendar.controller.dto.request.PlanRequest;
import com.kh.Calendar.controller.dto.response.PlanResponse;
import com.kh.Calendar.entity.Plan;
import com.kh.Calendar.entity.User;
import com.kh.Calendar.mapper.PlanMapper;
import com.kh.Calendar.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanMapper planMapper;
    private final UserMapper userMapper; // userNo로 User 객체 찾기 위해 필요

    @Override
    public Long addPlan(PlanRequest.AddPlanDto request) {
        User user = userMapper.findByUserNo(request.getUserNo());
        if (user == null) return null;

        Plan plan = request.toEntity(user);
        planMapper.addPlan(plan); // 실행 시 plan 객체 안에 planNo가 담김 (useGeneratedKeys 덕분)

        return plan.getPlanNo(); // 진짜 ID 반환!
    }

    @Override
    public int updatePlan(PlanRequest.UpdatePlanDto request) {
        Plan plan = Plan.builder()
                .planNo(request.getPlanNo())
                .planTitle(request.getPlanTitle())
                .planContent(request.getPlanContent())
                .build();
        return planMapper.updatePlan(plan);
    }

    @Override
    public int deletePlan(Long planNo) {
        return planMapper.deletePlan(planNo);
    }

    @Override
    public List<PlanResponse.PlanDto> searchPlans(Long userNo, String date, String keyword) {
        List<Plan> plans = planMapper.searchPlans(userNo, date, keyword);
        // Entity 리스트 -> DTO 리스트 변환해서 반환
        return plans.stream().map(PlanResponse.PlanDto::of).collect(Collectors.toList());
    }
}