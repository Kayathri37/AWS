package com.AWS.Figma.category.query;

import com.AWS.Figma.category.entity.HierarchyItem;
import com.AWS.Figma.category.entity.HierarchyType;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HierarchyQueryServiceImpl implements HierarchyQueryService {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<HierarchyItem> findBrandsByCategory(Long categoryId) {
        String jpql = "SELECT h FROM HierarchyItem h WHERE h.parent.id = :categoryId " +
                "AND h.type = :brandType AND h.active = true ORDER BY h.name";
        TypedQuery<HierarchyItem> query = entityManager.createQuery(jpql, HierarchyItem.class);
        query.setParameter("categoryId", categoryId);
        query.setParameter("brandType", HierarchyType.BRAND);
        return query.getResultList();
    }

    @Override
    public List<HierarchyItem> findTypesByBrand(Long brandId) {
        String jpql = "SELECT h FROM HierarchyItem h WHERE h.parent.id = :brandId " +
                "AND h.type = :typeType AND h.active = true ORDER BY h.name";
        TypedQuery<HierarchyItem> query = entityManager.createQuery(jpql, HierarchyItem.class);
        query.setParameter("brandId", brandId);
        query.setParameter("typeType", HierarchyType.TYPE);
        return query.getResultList();
    }

    @Override
    public List<HierarchyItem> buildFullHierarchy() {
        String jpql = "SELECT h FROM HierarchyItem h LEFT JOIN FETCH h.children c " +
                "WHERE h.parent IS NULL AND h.active = true ORDER BY h.name";
        return entityManager.createQuery(jpql, HierarchyItem.class).getResultList();
    }

    @Override
    public List<HierarchyItem> findByNameContaining(String name) {
        String jpql = "SELECT h FROM HierarchyItem h WHERE LOWER(h.name) LIKE LOWER(:name) " +
                "AND h.active = true ORDER BY h.name";
        TypedQuery<HierarchyItem> query = entityManager.createQuery(jpql, HierarchyItem.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public List<HierarchyItem> findItemsWithChildren() {
        String jpql = "SELECT DISTINCT h FROM HierarchyItem h WHERE EXISTS " +
                "(SELECT c FROM HierarchyItem c WHERE c.parent = h AND c.active = true) " +
                "AND h.active = true ORDER BY h.name";
        return entityManager.createQuery(jpql, HierarchyItem.class).getResultList();
    }

    @Override
    public List<HierarchyItem> findOrphanedItems() {
        String jpql = "SELECT h FROM HierarchyItem h WHERE h.parent IS NOT NULL " +
                "AND h.parent.active = false AND h.active = true";
        return entityManager.createQuery(jpql, HierarchyItem.class).getResultList();
    }

    @Override
    public Map<HierarchyType, Long> getCountByType() {
        String jpql = "SELECT h.type, COUNT(h) FROM HierarchyItem h WHERE h.active = true GROUP BY h.type";
        List<Object[]> results = entityManager.createQuery(jpql, Object[].class).getResultList();

        Map<HierarchyType, Long> countsByType = new HashMap<>();
        for (Object[] result : results) {
            countsByType.put((HierarchyType) result[0], (Long) result[1]);
        }
        return countsByType;
    }

    @Override
    public List<HierarchyItem> findRecentlyAdded(int days) {
        // Assuming you have a createdDate field in your entity
        String jpql = "SELECT h FROM HierarchyItem h WHERE h.active = true " +
                "ORDER BY h.id DESC";
        TypedQuery<HierarchyItem> query = entityManager.createQuery(jpql, HierarchyItem.class);
        query.setMaxResults(50); // Limit to recent 50 items
        return query.getResultList();
    }

    @Override
    public List<HierarchyItem> findTopLevelItems() {
        String jpql = "SELECT h FROM HierarchyItem h WHERE h.parent IS NULL AND h.active = true " +
                "ORDER BY h.name";
        return entityManager.createQuery(jpql, HierarchyItem.class).getResultList();
    }

    @Override
    public void softDeleteItem(Long id) {
        String jpql = "UPDATE HierarchyItem h SET h.active = false WHERE h.id = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void softDeleteWithChildren(Long id) {
        // First, soft delete all children recursively
        String jpql = "UPDATE HierarchyItem h SET h.active = false WHERE h.parent.id = :parentId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("parentId", id);
        query.executeUpdate();

        // Then soft delete the parent
        softDeleteItem(id);
    }

    @Override
    public Long countChildrenByParentId(Long parentId) {
        String jpql = "SELECT COUNT(h) FROM HierarchyItem h WHERE h.parent.id = :parentId AND h.active = true";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("parentId", parentId);
        return query.getSingleResult();
    }

    @Override
    public List<HierarchyItem> findSiblings(Long itemId) {
        String jpql = "SELECT h FROM HierarchyItem h WHERE h.parent.id = " +
                "(SELECT i.parent.id FROM HierarchyItem i WHERE i.id = :itemId) " +
                "AND h.id != :itemId AND h.active = true ORDER BY h.name";
        TypedQuery<HierarchyItem> query = entityManager.createQuery(jpql, HierarchyItem.class);
        query.setParameter("itemId", itemId);
        return query.getResultList();
    }

    @Override
    public List<HierarchyItem> findByLevel(Integer level) {
        String jpql = "SELECT h FROM HierarchyItem h WHERE h.level = :level AND h.active = true " +
                "ORDER BY h.name";
        TypedQuery<HierarchyItem> query = entityManager.createQuery(jpql, HierarchyItem.class);
        query.setParameter("level", level);
        return query.getResultList();
    }

    @Override
    public boolean hasChildren(Long itemId) {
        String jpql = "SELECT COUNT(h) FROM HierarchyItem h WHERE h.parent.id = :itemId AND h.active = true";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("itemId", itemId);
        return query.getSingleResult() > 0;
    }

    @Override
    public List<HierarchyItem> findLeafNodes() {
        String jpql = "SELECT h FROM HierarchyItem h WHERE h.active = true " +
                "AND NOT EXISTS (SELECT c FROM HierarchyItem c WHERE c.parent = h AND c.active = true) " +
                "ORDER BY h.name";
        return entityManager.createQuery(jpql, HierarchyItem.class).getResultList();
    }

    @Override
    public Map<Long, Integer> getHierarchyDepth() {
        String jpql = "SELECT h.id, h.level FROM HierarchyItem h WHERE h.active = true";
        List<Object[]> results = entityManager.createQuery(jpql, Object[].class).getResultList();

        Map<Long, Integer> depthMap = new HashMap<>();
        for (Object[] result : results) {
            depthMap.put((Long) result[0], (Integer) result[1]);
        }
        return depthMap;
    }
}