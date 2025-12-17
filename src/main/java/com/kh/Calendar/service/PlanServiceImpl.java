package com.kh.Calendar.service;

import com.kh.Calendar.dto.PlanRequestDto;
import com.kh.Calendar.dto.PlanResponseDto;
import com.kh.Calendar.entity.Plan;
import com.kh.Calendar.entity.User;
import com.kh.Calendar.repository.PlanRepository;
import com.kh.Calendar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public PlanResponseDto createPlan(Long userNo, PlanRequestDto requestDto) {
        User user = userRepository.findByUserNo(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));
        
        Plan plan = requestDto.toEntity(user);
        Plan savedPlan = planRepository.save(plan);
        return PlanResponseDto.from(savedPlan);
    }

    @Override
    @Transactional
    public PlanResponseDto updatePlan(Long planNo, PlanRequestDto requestDto) {
        Plan plan = planRepository.findByPlanNo(planNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정을 찾을 수 없습니다."));

        plan.update(requestDto.getPlanTitle(), requestDto.getPlanContent());

        return PlanResponseDto.from(plan);
    }

    @Override
    @Transactional
    public void deletePlan(Long planNo) {
        Plan plan = planRepository.findByPlanNo(planNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정을 찾을 수 없습니다."));
        planRepository.delete(plan);
    }

    @Override
    public List<PlanResponseDto> searchPlans(Long userNo, String date, String keyword) {
        List<Plan> plans = planRepository.searchPlans(userNo, date, keyword);
        return plans.stream()
                .map(PlanResponseDto::from)
                .collect(Collectors.toList());
    }
}
