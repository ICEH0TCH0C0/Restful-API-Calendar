package com.kh.Calendar.controller;

import com.kh.Calendar.dto.PlanRequestDto;
import com.kh.Calendar.dto.PlanResponseDto;
import com.kh.Calendar.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan")
public class PlanController {

    private final PlanService planService;

    // 일정 추가
    @PostMapping("/{userNo}")
    public ResponseEntity<PlanResponseDto> createPlan(@PathVariable Long userNo, @RequestBody PlanRequestDto requestDto) {
        PlanResponseDto responseDto = planService.createPlan(userNo, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 일정 수정
    @PatchMapping("/{planNo}")
    public ResponseEntity<PlanResponseDto> updatePlan(@PathVariable Long planNo, @RequestBody PlanRequestDto requestDto) {
        PlanResponseDto responseDto = planService.updatePlan(planNo, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 일정 삭제
    @DeleteMapping("/{planNo}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long planNo) {
        planService.deletePlan(planNo);
        return ResponseEntity.ok().build();
    }

    // 일정 검색
    @GetMapping("/search")
    public ResponseEntity<List<PlanResponseDto>> searchPlans(@RequestParam Long userNo,
                                                             @RequestParam String date,
                                                             @RequestParam(required = false) String keyword) {
        List<PlanResponseDto> dtoList = planService.searchPlans(userNo, date, keyword);
        return ResponseEntity.ok(dtoList);
    }
}
