package com.AWS.Figma.Dashboard.dao;
import com.AWS.Figma.Dashboard.entity.Item;
import com.AWS.Figma.Dashboard.entity.StockStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Long getTotalItemsCount() {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(i) FROM Item i", Long.class);
        return query.getSingleResult();
    }

    public Long getItemsCountByStatus(StockStatus status) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(i) FROM Item i WHERE i.status = :status", Long.class);
        query.setParameter("status", status);
        return query.getSingleResult();
    }

    public List<Item> getTopItemsByStatus(StockStatus status, int limit) {
        TypedQuery<Item> query = entityManager.createQuery(
                "SELECT i FROM Item i WHERE i.status = :status ORDER BY i.stockQuantity ASC", Item.class);
        query.setParameter("status", status);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<Item> getAllItemsByStatus(StockStatus status) {
        TypedQuery<Item> query = entityManager.createQuery(
                "SELECT i FROM Item i WHERE i.status = :status ORDER BY i.stockQuantity ASC", Item.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    public Item save(Item item) {
        if (item.getId() == null) {
            entityManager.persist(item);
            return item;
        } else {
            return entityManager.merge(item);
        }
    }

    public Item findById(Long id) {
        return entityManager.find(Item.class, id);
    }

    public List<Item> findAll() {
        TypedQuery<Item> query = entityManager.createQuery(
                "SELECT i FROM Item i ORDER BY i.description", Item.class);
        return query.getResultList();
    }
}