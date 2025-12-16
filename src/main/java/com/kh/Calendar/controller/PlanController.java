package com.kh.Calendar.controller;

import com.kh.Calendar.controller.dto.request.PlanRequest;
import com.kh.Calendar.controller.dto.response.PlanResponse;
import com.kh.Calendar.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan")
public class PlanController {

    private final PlanService planService;

    // 일정 추가
    @PostMapping("/add")
    public ResponseEntity<?> addPlan(@RequestBody PlanRequest.AddPlanDto request) {
        Long planNo = planService.addPlan(request);

        if (planNo != null) {
            return ResponseEntity.ok(planNo); // 프론트엔드에게 진짜 ID를 줌
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("실패");
        }
    }

    // 일정 수정
    @PatchMapping("/update")
    public ResponseEntity<?> updatePlan(@RequestBody PlanRequest.UpdatePlanDto request) {
        int result = planService.updatePlan(request);
        if (result > 0) {
            return ResponseEntity.ok("일정 수정 성공");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("실패");
        }
    }

    // 일정 삭제
    @DeleteMapping("/delete/{planNo}")
    public ResponseEntity<?> deletePlan(@PathVariable Long planNo) {
        int result = planService.deletePlan(planNo);

        if (result > 0) {
            return ResponseEntity.ok("일정 삭제 성공");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPlans(@RequestParam Long userNo,
                                         @RequestParam String date,
                                         @RequestParam(required = false, defaultValue = "") String keyword) {

        List<PlanResponse.PlanDto> list = planService.searchPlans(userNo, date, keyword);
        return ResponseEntity.ok(list);
    }
}