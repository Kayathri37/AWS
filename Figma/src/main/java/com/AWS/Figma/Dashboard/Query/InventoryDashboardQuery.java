package com.AWS.Figma.Dashboard.Query;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class InventoryDashboardQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public long getTotalItemCount() {
        String jpql = "SELECT COUNT(i) FROM InventoryItem i";
        return (Long) entityManager.createQuery(jpql).getSingleResult();
    }
}
