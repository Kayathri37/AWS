package com.AWS.Figma.Category.Service;


import com.AWS.Figma.Category.DAO.CategoryDao;
import com.AWS.Figma.Category.DAO.TypeDao;
import com.AWS.Figma.Category.Dto.CreateBrandRequest;
import com.AWS.Figma.Category.Dto.CreateCategoryRequest;
import com.AWS.Figma.Category.Dto.EditCategoryRequest;
import com.AWS.Figma.Category.Entity.Brand;
import com.AWS.Figma.Category.Entity.Category;
import com.AWS.Figma.Category.Facade.CategoryValidationFacade;
import com.AWS.Figma.Category.Facade.TypeValidationFacade;
import com.AWS.Figma.Category.Repository.BrandRepository;
import com.AWS.Figma.Category.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private BrandRepository brandRepo;

    @Autowired
    private TypeValidationFacade validationFacade;

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private CategoryDao dao;

    @Autowired
    private CategoryValidationFacade validation;

    public Category createCategory(CreateCategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        return categoryRepo.save(category);
    }

    public Optional<Category> findCategoryById(Long id) {
        return categoryRepo.findById(id);
    }

    public Brand createBrand(CreateBrandRequest request) {
        Category category = categoryRepo.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + request.getCategoryId()));

        Brand brand = new Brand();
        brand.setName(request.getBrandName());
        brand.setCategory(category);
        return brandRepo.save(brand);
    }


    public Category editCategory(Long categoryId, EditCategoryRequest request) {
        Category category = validation.validateAndGetCategory(categoryId);
        category.setName(request.getName());
        return dao.editCategory(category);
    }
}


