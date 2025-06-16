package com.AWS.Figma.category.service;

import com.AWS.Figma.category.dto.CategoryRequestDto;
import com.AWS.Figma.category.dto.CategoryResponseDto;

public interface CategoryService {
    CategoryResponseDto addCategory(CategoryRequestDto requestDto);
    CategoryResponseDto updateCategory(Long id, CategoryRequestDto requestDto);
}
