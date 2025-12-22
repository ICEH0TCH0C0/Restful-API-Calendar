package com.kh.Calendar.service;

import com.kh.Calendar.dto.CategoryRequestDto;
import com.kh.Calendar.dto.CategoryResponseDto; // import 추가
import com.kh.Calendar.entity.Category;
import com.kh.Calendar.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDto> findAll() {
        // 엔티티 목록을 가져와서 DTO 목록으로 변환
        return categoryRepository.findAll().stream()
                .map(CategoryResponseDto::of)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryResponseDto createCategory(CategoryRequestDto dto) {
        // 중복 카테고리 이름 체크
        if (categoryRepository.findByName(dto.getCategoryName()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
        }

        Category category = categoryRepository.save(dto.toEntity());
        // 저장된 엔티티를 DTO로 변환해서 반환
        return CategoryResponseDto.of(category);
    }
}