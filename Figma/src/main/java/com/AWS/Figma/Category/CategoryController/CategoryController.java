package com.AWS.Figma.Category.CategoryController;

import com.AWS.Figma.Category.Dto.*;
import com.AWS.Figma.Category.Entity.Brand;
import com.AWS.Figma.Category.Entity.Category;
import com.AWS.Figma.Category.Entity.Type;
import com.AWS.Figma.Category.Repository.BrandRepository;
import com.AWS.Figma.Category.Repository.CategoryRepository;
import com.AWS.Figma.Category.Repository.TypeRepository;
import com.AWS.Figma.Category.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepo;
    private BrandRepository brandRepo;
    private CategoryService categoryService;
    private TypeRepository typeRepo;
    private CategoryService service;


    public CategoryController(CategoryRepository categoryRepo, BrandRepository brandRepo,CategoryService categoryService,TypeRepository typeRepo,CategoryService service) {
        this.categoryRepo = categoryRepo;
        this.brandRepo = brandRepo;
        this.categoryService=categoryService;
        this.typeRepo=typeRepo;
        this.service=service;
    }

    @PostMapping("/createCategory")
    public ResponseEntity<?> createCategory(
            @RequestBody CreateCategoryRequest request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Missing or malformed Authorization header");
        }

        if (request.getName() == null || request.getName().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Category name is required");
        }

        Category savedCategory = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }


    @PostMapping("/createBrand")
    public ResponseEntity<?> createBrand(
            @Valid @RequestBody CreateBrandRequest request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Missing or malformed Authorization header");
        }

        Category category = categoryRepo.findById(request.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Category not found with ID: " + request.getCategoryId());
        }

        try {
            Brand brand = categoryService.createBrand(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(brand);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PostMapping("/createType")
    public ResponseEntity<?> createType(
            @Valid @RequestBody CreateTypeRequest request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Missing or malformed Authorization header");
        }

        Brand brand = brandRepo.findById(request.getBrandId()).orElse(null);
        if (brand == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Brand not found with ID: " + request.getBrandId());
        }

        try {
            Type type = categoryService.createType(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(type);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/editCategory/{id}")
    public ResponseEntity<?> editCategory(
            @PathVariable Long id,
            @Valid @RequestBody EditCategoryRequest request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Missing or malformed Authorization header");
        }

        try {
            Category updated = service.editCategory(id, request);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    @PutMapping("/editBrand/{categoryId}")
    public ResponseEntity<?> editBrand(@PathVariable Long categoryId,
                                       @RequestBody EditBrandRequest request,
                                       @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Missing or malformed Authorization header");
        }

        try {
            Brand updated = categoryService.editBrand(categoryId, request);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }


}



