package com.AWS.Figma.category.query;

import com.AWS.Figma.category.entity.HierarchyItem;
import com.AWS.Figma.category.entity.HierarchyType;
import java.util.List;
import java.util.Map;

public interface HierarchyQueryService {
    List<HierarchyItem> findBrandsByCategory(Long categoryId);
    List<HierarchyItem> findTypesByBrand(Long brandId);
    List<HierarchyItem> buildFullHierarchy();
    List<HierarchyItem> findByNameContaining(String name);
    List<HierarchyItem> findItemsWithChildren();
    List<HierarchyItem> findOrphanedItems();
    Map<HierarchyType, Long> getCountByType();
    List<HierarchyItem> findRecentlyAdded(int days);
    List<HierarchyItem> findTopLevelItems();
    void softDeleteItem(Long id);
    void softDeleteWithChildren(Long id);
    Long countChildrenByParentId(Long parentId);
    List<HierarchyItem> findSiblings(Long itemId);
    List<HierarchyItem> findByLevel(Integer level);
    boolean hasChildren(Long itemId);
    List<HierarchyItem> findLeafNodes();
    Map<Long, Integer> getHierarchyDepth();
}