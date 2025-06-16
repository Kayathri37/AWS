package com.AWS.Figma.category.dao;


import com.AWS.Figma.category.entity.Category;

public interface CategoryDao {
    Category saveCategory(Category category);
    Category updateCategory(Category category);
    Category findById(Long id);
}
