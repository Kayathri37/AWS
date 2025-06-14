package com.AWS.Figma.Category.Service;


import com.AWS.Figma.Category.DAO.BrandDao;
import com.AWS.Figma.Category.DAO.CategoryDao;
import com.AWS.Figma.Category.DAO.TypeDao;
import com.AWS.Figma.Category.Dto.*;
import com.AWS.Figma.Category.Entity.Brand;
import com.AWS.Figma.Category.Entity.Category;
import com.AWS.Figma.Category.Entity.Type;
import com.AWS.Figma.Category.Facade.BrandValitationFacade;
import com.AWS.Figma.Category.Facade.CategoryValidationFacade;
import com.AWS.Figma.Category.Facade.TypeValidationFacade;
import com.AWS.Figma.Category.Repository.BrandRepository;
import com.AWS.Figma.Category.Repository.CategoryRepository;
import com.AWS.Figma.Category.Repository.TypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private BrandValitationFacade brandValitationFacade;

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private TypeRepository typeRepo;

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
    public Type createType(CreateTypeRequest request) {
        Brand brand = brandRepo.findById(request.getBrandId())
                .orElseThrow(() -> new IllegalArgumentException("Brand not found"));

        Type type = new Type();
        type.setName(request.getTypeName());
        type.setBrand(brand);

        return typeRepo.save(type);
    }



    public Category editCategory(Long categoryId, EditCategoryRequest request) {
        Category category = validation.validateAndGetCategory(categoryId);
        category.setName(request.getName());
        return dao.editCategory(category);
    }
    public Brand editBrand(Long categoryId, EditBrandRequest req) {

        Brand brand = brandValitationFacade
                .validateAndGetBrand(req.getBrandId(), categoryId);
        brand.setName(req.getBrandName());
        return brandRepo.save(brand);
    }
}