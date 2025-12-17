package com.kh.Calendar.repository;

import com.kh.Calendar.entity.Plan;
import java.util.List;
import java.util.Optional;

public interface PlanRepository {

    // 일정 등록
    Plan save(Plan plan);

    // 일정 조회 (PK로)
    Optional<Plan> findByPlanNo(Long planNo);

    // 일정 삭제
    void delete(Plan plan);

    // 일정 검색 (사용자 번호, 날짜, 키워드)
    List<Plan> searchPlans(Long userNo, String date, String keyword);
}
