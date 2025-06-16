package com.AWS.Figma.category.dao;




import com.AWS.Figma.category.entity.Category;

import com.AWS.Figma.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return repository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
