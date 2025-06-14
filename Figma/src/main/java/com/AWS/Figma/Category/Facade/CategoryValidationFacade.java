package com.AWS.Figma.Category.Facade;

import com.AWS.Figma.Category.DAO.CategoryDao;
import com.AWS.Figma.Category.Entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidationFacade {

    @Autowired
    private CategoryDao dao;

    public Category validateAndGetCategory(Long id) {
        Category category = dao.findById(id);
        if (category == null) {
            throw new IllegalArgumentException("Category not found with ID: " + id);
        }
        return category;
    }
}