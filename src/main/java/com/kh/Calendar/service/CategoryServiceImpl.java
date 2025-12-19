package com.kh.Calendar.service;

import com.kh.Calendar.dto.CategoryRequestDto;
import com.kh.Calendar.entity.Category;
import com.kh.Calendar.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional // 쓰기 전용
    public Category createCategory(CategoryRequestDto dto) {
        return categoryRepository.save(dto.toEntity());
    }
}