package com.kh.Calendar.mapper;

import com.kh.Calendar.entity.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlanMapper {
    int addPlan(Plan plan);
    int updatePlan(Plan plan);
    int deletePlan(Long planNo);
    List<Plan> searchPlans(@Param("userNo") Long userNo,
                           @Param("date") String date,
                           @Param("keyword") String keyword);
}