package com.kh.Calendar.dto;

import com.kh.Calendar.entity.Category;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {
    private String categoryName;

    public Category toEntity() {
        return Category.builder()
                .categoryName(categoryName)
                .build();
    }
}