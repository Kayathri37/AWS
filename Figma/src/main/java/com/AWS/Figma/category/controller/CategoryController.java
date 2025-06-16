package com.AWS.Figma.category.controller;

import com.AWS.Figma.category.dto.CategoryRequestDto;
import com.AWS.Figma.category.dto.CategoryResponseDto;
import com.AWS.Figma.category.facade.CategoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryFacade facade;

    @PostMapping("/add")
    public ResponseEntity<CategoryResponseDto> addCategory(@RequestBody CategoryRequestDto dto) {
        return ResponseEntity.ok(facade.addCategory(dto));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CategoryResponseDto> editCategory(@PathVariable Long id, @RequestBody CategoryRequestDto dto) {
        return ResponseEntity.ok(facade.editCategory(id, dto));
    }
}
