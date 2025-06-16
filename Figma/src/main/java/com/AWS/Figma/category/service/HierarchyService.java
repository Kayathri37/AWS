package com.AWS.Figma.category.service;

import com.AWS.Figma.category.dto.HierarchyRequestDto;
import com.AWS.Figma.category.dto.HierarchyResponseDto;
import com.AWS.Figma.category.entity.HierarchyType;
import com.AWS.Figma.dto.*;

import java.util.List;

public interface HierarchyService {
    ResponseDto addItem(HierarchyRequestDto requestDto);
    ResponseDto editItem(Long id, HierarchyRequestDto requestDto);
    List<HierarchyResponseDto> getAllItems();
    List<HierarchyResponseDto> getItemsByType(HierarchyType type);
    List<HierarchyResponseDto> getItemsByParent(Long categoryId);
    List<HierarchyResponseDto> getCategories();
    List<HierarchyResponseDto> getBrandsByCategory(Long categoryId);
    List<HierarchyResponseDto> getTypesByBrand(Long brandId);
    HierarchyResponseDto getItemById(Long id);
    ResponseDto deleteItem(Long id);
    List<HierarchyResponseDto> getFullHierarchy();
}