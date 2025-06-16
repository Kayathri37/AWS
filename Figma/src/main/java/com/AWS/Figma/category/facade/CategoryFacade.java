package com.AWS.Figma.category.facade;

import com.AWS.Figma.category.dto.CategoryRequestDto;
import com.AWS.Figma.category.dto.CategoryResponseDto;
import com.AWS.Figma.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryFacade {

    @Autowired
    private CategoryService service;

    public CategoryResponseDto addCategory(CategoryRequestDto dto) {
        return service.addCategory(dto);
    }

    public CategoryResponseDto editCategory(Long id, CategoryRequestDto dto) {
        return service.updateCategory(id, dto);
    }
}
