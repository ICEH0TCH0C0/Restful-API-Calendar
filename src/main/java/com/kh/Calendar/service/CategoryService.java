package com.kh.Calendar.service;

import com.kh.Calendar.dto.CategoryRequestDto;
import com.kh.Calendar.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category createCategory(CategoryRequestDto dto);
}