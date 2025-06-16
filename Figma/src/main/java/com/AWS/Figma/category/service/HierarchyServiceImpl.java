package com.AWS.Figma.category.service;

import com.AWS.Figma.category.dto.HierarchyRequestDto;
import com.AWS.Figma.category.dto.HierarchyResponseDto;
import com.AWS.Figma.category.entity.HierarchyItem;
import com.AWS.Figma.category.entity.HierarchyType;
import com.AWS.Figma.category.repo.HierarchyRepository;
import com.AWS.Figma.dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HierarchyServiceImpl implements HierarchyService {

    @Autowired
    private HierarchyRepository hierarchyRepository;

    @Override
    public ResponseDto addItem(HierarchyRequestDto requestDto) {
        // Validation based on type
        if (requestDto.getType() == HierarchyType.CATEGORY) {
            // Categories should not have parent
            if (requestDto.getCategoryId() != null) {
                return new ResponseDto("Error: Categories cannot have a parent", false);
            }

            if (hierarchyRepository.existsByNameAndTypeAndParentIsNull(
                    requestDto.getName(), HierarchyType.CATEGORY)) {
                return new ResponseDto("Error: Category name already exists", false);
            }
        } else {
            // Brands and Types must have parent
            if (requestDto.getCategoryId() == null) {
                return new ResponseDto("Error: " + requestDto.getType() + " must have a parent", false);
            }


            HierarchyItem parent = hierarchyRepository.findById(requestDto.getCategoryId())
                    .orElse(null);

            if (parent == null) {
                return new ResponseDto("Error: Parent not found", false);
            }

            // Validate parent type
            if (requestDto.getType() == HierarchyType.BRAND && parent.getType() != HierarchyType.CATEGORY) {
                return new ResponseDto("Error: Brand parent must be a Category", false);
            }

            if (requestDto.getType() == HierarchyType.TYPE && parent.getType() != HierarchyType.BRAND) {
                return new ResponseDto("Error: Type parent must be a Brand", false);
            }

            if (hierarchyRepository.existsByNameAndTypeAndParentId(
                    requestDto.getName(), requestDto.getType(), requestDto.getCategoryId())) {
                return new ResponseDto("Error: " + requestDto.getType() + " name already exists for this parent", false);
            }
        }

        HierarchyItem item = requestDto.toEntity();

        if (requestDto.getCategoryId() != null) {
            HierarchyItem parent = hierarchyRepository.findById(requestDto.getCategoryId()).get();
            item.setParent(parent);
        }

        hierarchyRepository.save(item);
        return new ResponseDto(requestDto.getType() + " added successfully", true);
    }

    @Override
    public ResponseDto editItem(Long id, HierarchyRequestDto requestDto) {
        HierarchyItem item = hierarchyRepository.findById(id).orElse(null);

        if (item == null) {
            return new ResponseDto("Error: Item not found", false);
        }

        // Type cannot be changed
        if (item.getType() != requestDto.getType()) {
            return new ResponseDto("Error: Item type cannot be changed", false);
        }

        // Validate name uniqueness
        boolean nameExists = false;
        if (requestDto.getType() == HierarchyType.CATEGORY) {
            nameExists = hierarchyRepository.existsByNameAndTypeAndParentIsNull(
                    requestDto.getName(), HierarchyType.CATEGORY) &&
                    !item.getName().equals(requestDto.getName());
        } else {
            nameExists = hierarchyRepository.existsByNameAndTypeAndParentId(
                    requestDto.getName(), requestDto.getType(), requestDto.getCategoryId()) &&
                    !item.getName().equals(requestDto.getName());
        }

        if (nameExists) {
            return new ResponseDto("Error: " + requestDto.getType() + " name already exists", false);
        }

        // Update parent if provided and different
        if (requestDto.getCategoryId() != null &&
                (item.getParent() == null || !item.getParent().getId().equals(requestDto.getCategoryId()))) {

            HierarchyItem newParent = hierarchyRepository.findById(requestDto.getCategoryId())
                    .orElse(null);

            if (newParent == null) {
                return new ResponseDto("Error: Parent not found", false);
            }

            item.setParent(newParent);
        }

        item.setName(requestDto.getName());
        hierarchyRepository.save(item);
        return new ResponseDto(requestDto.getType() + " updated successfully", true);
    }

    @Override
    public List<HierarchyResponseDto> getAllItems() {
        return hierarchyRepository.findAll().stream()
                .filter(HierarchyItem::isActive)
                .map(HierarchyResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<HierarchyResponseDto> getItemsByType(HierarchyType type) {
        return hierarchyRepository.findByTypeAndActiveTrue(type).stream()
                .map(HierarchyResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<HierarchyResponseDto> getItemsByParent(Long categoryId) {
        return hierarchyRepository.findByParentIdAndActiveTrue(categoryId).stream()
                .map(HierarchyResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<HierarchyResponseDto> getCategories() {
        return getItemsByType(HierarchyType.CATEGORY);
    }

    @Override
    public List<HierarchyResponseDto> getBrandsByCategory(Long categoryId) {
        return getItemsByParent(categoryId);
    }

    @Override
    public List<HierarchyResponseDto> getTypesByBrand(Long brandId) {
        return getItemsByParent(brandId);
    }

    @Override
    public HierarchyResponseDto getItemById(Long id) {
        HierarchyItem item = hierarchyRepository.findByIdWithChildren(id).orElse(null);
        return item != null ? HierarchyResponseDto.fromEntity(item) : null;
    }

    @Override
    public ResponseDto deleteItem(Long id) {
        HierarchyItem item = hierarchyRepository.findById(id).orElse(null);
        if (item == null) {
            return new ResponseDto("Error: Item not found", false);
        }

        item.setActive(false);
        hierarchyRepository.save(item);
        return new ResponseDto(item.getType() + " deleted successfully", true);
    }

    @Override
    public List<HierarchyResponseDto> getFullHierarchy() {
        return hierarchyRepository.findByParentIsNullAndActiveTrue().stream()
                .map(HierarchyResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}