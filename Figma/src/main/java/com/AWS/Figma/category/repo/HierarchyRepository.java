package com.AWS.Figma.category.repo;


import com.AWS.Figma.category.entity.HierarchyItem;
import com.AWS.Figma.category.entity.HierarchyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface HierarchyRepository extends JpaRepository<HierarchyItem, Long> {

    // Find by type
    List<HierarchyItem> findByTypeAndActiveTrue(HierarchyType type);

    // Find by parent
    List<HierarchyItem> findByParentIdAndActiveTrue(Long categoryId);

    // Find root items (categories)
    List<HierarchyItem> findByParentIsNullAndActiveTrue();

    // Check if name exists at same level with same parent
    boolean existsByNameAndTypeAndParentId(String name, HierarchyType type, Long categoryId);

    // Check if name exists for root level (categories)
    boolean existsByNameAndTypeAndParentIsNull(String name, HierarchyType type);

    // Find with children
    @Query("SELECT h FROM HierarchyItem h LEFT JOIN FETCH h.children WHERE h.id = :id")
    Optional<HierarchyItem> findByIdWithChildren(@Param("id") Long id);

    // Find hierarchy path
    @Query("SELECT h FROM HierarchyItem h WHERE h.id = :id OR h.parent.id = :id OR h.parent.parent.id = :id")
    List<HierarchyItem> findHierarchyPath(@Param("id") Long id);
}