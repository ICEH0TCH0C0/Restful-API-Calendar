package com.kh.Calendar.dto;

import com.kh.Calendar.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {
    private Long categoryNo;
    private String categoryName;

    public static CategoryResponseDto of(Category category) {
        return CategoryResponseDto.builder()
                .categoryNo(category.getCategoryNo())
                .categoryName(category.getCategoryName())
                .build();
    }
}