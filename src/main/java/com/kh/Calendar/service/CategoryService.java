package com.kh.Calendar.service;

import com.kh.Calendar.dto.CategoryRequestDto;
import com.kh.Calendar.dto.CategoryResponseDto; // import 추가

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDto> findAll();

    CategoryResponseDto createCategory(CategoryRequestDto dto);
}