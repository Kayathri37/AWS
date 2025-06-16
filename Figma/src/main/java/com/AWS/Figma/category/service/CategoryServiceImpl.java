package com.AWS.Figma.category.service;

import com.AWS.Figma.category.dao.CategoryDao;
import com.AWS.Figma.category.dto.CategoryRequestDto;
import com.AWS.Figma.category.dto.CategoryResponseDto;
import com.AWS.Figma.category.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao dao;

    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto dto) {
        Category category = Category.builder()
                .categoryName(dto.getCategoryName())
                .type(dto.getType())
                .brand(dto.getBrand())
                .build();
        return mapToResponse(dao.saveCategory(category));
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto dto) {
        Category category = dao.findById(id);
        if (category != null) {
            category.setCategoryName(dto.getCategoryName());
            category.setType(dto.getType());
            category.setBrand(dto.getBrand());
            return mapToResponse(dao.updateCategory(category));
        }
        return null;
    }

    private CategoryResponseDto mapToResponse(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .type(category.getType())
                .brand(category.getBrand())
                .build();
    }
}
