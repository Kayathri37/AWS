
package com.AWS.Figma.category.dao;

import com.AWS.Figma.category.entity.HierarchyItem;
import com.AWS.Figma.category.entity.HierarchyType;
import java.util.List;
import java.util.Optional;

public interface HierarchyDao {
    HierarchyItem save(HierarchyItem item);
    Optional<HierarchyItem> findById(Long id);
    List<HierarchyItem> findAll();
    List<HierarchyItem> findByType(HierarchyType type);
    List<HierarchyItem> findByParentId(Long parentId);
    List<HierarchyItem> findCategories();
    List<HierarchyItem> findBrandsByCategory(Long categoryId);
    List<HierarchyItem> findTypesByBrand(Long brandId);
    List<HierarchyItem> findFullHierarchy();
    boolean existsByNameAndType(String name, HierarchyType type, Long parentId);
    void deleteById(Long id);
    void softDelete(Long id);
    List<HierarchyItem> findByNameContaining(String name);
    List<HierarchyItem> findHierarchyPath(Long id);
    Long countByParentId(Long parentId);
    List<HierarchyItem> findTopLevelItems();
}