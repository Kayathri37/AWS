package com.AWS.Figma.category.dao;


import com.AWS.Figma.category.entity.HierarchyItem;
import com.AWS.Figma.category.entity.HierarchyType;
import com.AWS.Figma.category.query.HierarchyQueryService;
import com.AWS.Figma.category.repo.HierarchyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class HierarchyDaoImpl implements HierarchyDao {

    @Autowired
    private HierarchyRepository hierarchyRepository;

    @Autowired
    private HierarchyQueryService queryService;

    @Override
    public HierarchyItem save(HierarchyItem item) {
        return hierarchyRepository.save(item);
    }

    @Override
    public Optional<HierarchyItem> findById(Long id) {
        return hierarchyRepository.findById(id);
    }

    @Override
    public List<HierarchyItem> findAll() {
        return hierarchyRepository.findAll();
    }

    @Override
    public List<HierarchyItem> findByType(HierarchyType type) {
        return hierarchyRepository.findByTypeAndActiveTrue(type);
    }

    @Override
    public List<HierarchyItem> findByParentId(Long parentId) {
        return hierarchyRepository.findByParentIdAndActiveTrue(parentId);
    }

    @Override
    public List<HierarchyItem> findCategories() {
        return hierarchyRepository.findByParentIsNullAndActiveTrue();
    }

    @Override
    public List<HierarchyItem> findBrandsByCategory(Long categoryId) {
        return queryService.findBrandsByCategory(categoryId);
    }

    @Override
    public List<HierarchyItem> findTypesByBrand(Long brandId) {
        return queryService.findTypesByBrand(brandId);
    }

    @Override
    public List<HierarchyItem> findFullHierarchy() {
        return queryService.buildFullHierarchy();
    }

    @Override
    public boolean existsByNameAndType(String name, HierarchyType type, Long parentId) {
        if (parentId == null) {
            return hierarchyRepository.existsByNameAndTypeAndParentIsNull(name, type);
        }
        return hierarchyRepository.existsByNameAndTypeAndParentId(name, type, parentId);
    }

    @Override
    public void deleteById(Long id) {
        hierarchyRepository.deleteById(id);
    }

    @Override
    public void softDelete(Long id) {
        queryService.softDeleteItem(id);
    }

    @Override
    public List<HierarchyItem> findByNameContaining(String name) {
        return queryService.findByNameContaining(name);
    }

    @Override
    public List<HierarchyItem> findHierarchyPath(Long id) {
        return hierarchyRepository.findHierarchyPath(id);
    }

    @Override
    public Long countByParentId(Long parentId) {
        return queryService.countChildrenByParentId(parentId);
    }

    @Override
    public List<HierarchyItem> findTopLevelItems() {
        return queryService.findTopLevelItems();
    }
}
