package com.kh.Calendar.controller;

import com.kh.Calendar.dto.CategoryRequestDto;
import com.kh.Calendar.dto.CategoryResponseDto;
import com.kh.Calendar.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequestDto requestDto) {
        try {
            CategoryResponseDto createdCategory = categoryService.createCategory(requestDto);
            return ResponseEntity.ok(createdCategory);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorBody = Collections.singletonMap("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorBody);
        }
    }
}