package com.AWS.Figma.category.controller;

import com.AWS.Figma.category.dto.HierarchyRequestDto;
import com.AWS.Figma.category.dto.HierarchyResponseDto;
import com.AWS.Figma.category.entity.HierarchyType;
import com.AWS.Figma.category.service.HierarchyService;
import com.AWS.Figma.dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hierarchy")
public class HierarchyController {

    @Autowired
    private HierarchyService hierarchyService;

    @PostMapping
    public ResponseEntity<ResponseDto> addItem(@RequestBody HierarchyRequestDto requestDto) {
        return ResponseEntity.ok(hierarchyService.addItem(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> editItem(@PathVariable Long id,
                                                @RequestBody HierarchyRequestDto requestDto) {
        return ResponseEntity.ok(hierarchyService.editItem(id, requestDto));
    }

    @GetMapping
    public ResponseEntity<List<HierarchyResponseDto>> getAllItems() {
        return ResponseEntity.ok(hierarchyService.getAllItems());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<HierarchyResponseDto>> getItemsByType(@PathVariable HierarchyType type) {
        return ResponseEntity.ok(hierarchyService.getItemsByType(type));
    }

    @GetMapping("/parent/{categoryId}")
    public ResponseEntity<List<HierarchyResponseDto>> getItemsByParent(@PathVariable Long categoryId) {
        return ResponseEntity.ok(hierarchyService.getItemsByParent(categoryId));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<HierarchyResponseDto>> getCategories() {
        return ResponseEntity.ok(hierarchyService.getCategories());
    }

    @GetMapping("/brands/category/{categoryId}")
    public ResponseEntity<List<HierarchyResponseDto>> getBrandsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(hierarchyService.getBrandsByCategory(categoryId));
    }

    @GetMapping("/types/brand/{brandId}")
    public ResponseEntity<List<HierarchyResponseDto>> getTypesByBrand(@PathVariable Long brandId) {
        return ResponseEntity.ok(hierarchyService.getTypesByBrand(brandId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HierarchyResponseDto> getItemById(@PathVariable Long id) {
        HierarchyResponseDto item = hierarchyService.getItemById(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteItem(@PathVariable Long id) {
        return ResponseEntity.ok(hierarchyService.deleteItem(id));
    }

    @GetMapping("/full-hierarchy")
    public ResponseEntity<List<HierarchyResponseDto>> getFullHierarchy() {
        return ResponseEntity.ok(hierarchyService.getFullHierarchy());
    }
}