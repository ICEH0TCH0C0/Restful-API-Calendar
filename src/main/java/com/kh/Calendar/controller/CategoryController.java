package com.kh.Calendar.controller;

import com.kh.Calendar.dto.CategoryRequestDto;
import com.kh.Calendar.entity.Category;
import com.kh.Calendar.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    // [추가] 카테고리 생성 API
    @PostMapping()
    public ResponseEntity<Category> addCategory(@RequestBody CategoryRequestDto requestDto) {
        Category savedCategory = categoryService.createCategory(requestDto);
        return ResponseEntity.ok(savedCategory);
    }
}