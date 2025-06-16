package com.AWS.Figma.Dashboard.Query;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryDashboardQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public int fetchTotalItems() {
        String jpql = "SELECT COUNT(i) FROM InventoryItem i";
        return ((Number) entityManager.createQuery(jpql)
                .getSingleResult()).intValue();
    }

    public int fetchNearingStockItems() {
        String jpql = """
                
                SELECT COUNT(i) FROM InventoryItem i WHERE i.quantity < 10 AND i.quantity > 0""";
        return ((Number) entityManager.createQuery(jpql)
                .getSingleResult()).intValue();
    }

    public int fetchOutOfStockItems() {
        String jpql = "SELECT COUNT(i) FROM InventoryItem i WHERE i.quantity = 0";
        return ((Number) entityManager.createQuery(jpql)
                .getSingleResult()).intValue();
    }
}
