package com.AWS.Figma.Dashboard.Query;

import com.AWS.Figma.InventoryManagement.Dto.StockStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class NearingStockQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public long fetchNearingStockCount() {
        String jpql = "SELECT COUNT(i) FROM InventoryItem i WHERE i.status = :status";
        return (Long) entityManager.createQuery(jpql)
                                   .setParameter("status", StockStatus.NEARING_STOCK)
                                   .getSingleResult();
    }
}
