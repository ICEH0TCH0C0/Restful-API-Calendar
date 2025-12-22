package com.kh.Calendar.service;

import com.kh.Calendar.dto.PlanRequestDto;
import com.kh.Calendar.dto.PlanResponseDto;
import com.kh.Calendar.entity.Category;
import com.kh.Calendar.entity.Plan;
import com.kh.Calendar.entity.User;
import com.kh.Calendar.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public PlanResponseDto createPlan(Long userNo, PlanRequestDto requestDto) {
        User user = userRepository.findByUserNo(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));

        // Plan 엔티티 생성
        Plan plan = requestDto.toEntity(user);

        // 카테고리 조회 및 설정
        if (requestDto.getCategoryNo() != null) {
            Category category = categoryRepository.findById(requestDto.getCategoryNo())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
            plan.setCategory(category); // 아까 추가한 setter 사용
        }

        Plan savedPlan = planRepository.save(plan);
        return PlanResponseDto.of(savedPlan);
    }

    @Override
    @Transactional
    public PlanResponseDto updatePlan(Long planNo, PlanRequestDto requestDto) {
        Plan plan = planRepository.findByPlanNo(planNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정을 찾을 수 없습니다."));

        // 수정 시에도 카테고리 조회
        Category category = null;
        if (requestDto.getCategoryNo() != null) {
            category = categoryRepository.findById(requestDto.getCategoryNo())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
        }

        // 업데이트 메소드에 category 전달
        plan.update(requestDto.getPlanTitle(), requestDto.getPlanContent(), category);

        return PlanResponseDto.of(plan);
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
                .map(PlanResponseDto::of)
                // .map((plan) -> PlanResponseDto.of(plan))
                .collect(Collectors.toList());
    }
}
