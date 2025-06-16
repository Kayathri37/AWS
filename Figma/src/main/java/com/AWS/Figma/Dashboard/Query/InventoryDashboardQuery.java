package com.AWS.Figma.Dashboard.Query;

import com.AWS.Figma.Dashboard.DTO.NearingStockItemDto;
import com.AWS.Figma.InventoryManagement.Dto.StockStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<NearingStockItemDto> fetchTop5NearingStockItems() {
        String jpql = """
            SELECT new com.AWS.Figma.Dashboard.DTO.NearingStockItemDto(
                     i.id, i.description, i.status)
            FROM InventoryItem i
            WHERE i.status = :status
            ORDER BY i.quantity ASC
            """;

        return entityManager.createQuery(jpql, NearingStockItemDto.class)
                .setParameter("status", StockStatus.NEARING_STOCK)
                .setMaxResults(5)
                .getResultList();
    }
}
