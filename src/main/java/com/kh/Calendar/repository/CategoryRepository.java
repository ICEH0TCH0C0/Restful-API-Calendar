package com.kh.Calendar.repository;

import com.kh.Calendar.entity.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    // 카테고리 목록 전체 조회
    List<Category> findAll();

    // ID로 카테고리 하나 조회
    Optional<Category> findById(Long categoryNo);

    // 저장
    Category save(Category category);
}