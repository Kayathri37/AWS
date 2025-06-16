package com.AWS.Figma.Dashboard.query;
import com.AWS.Figma.Dashboard.entity.Item;
import com.AWS.Figma.Dashboard.entity.StockStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ItemQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public Long getTotalItemsCount() {
        String jpql = "SELECT COUNT(i) FROM Item i";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        return query.getSingleResult();
    }

    public Long getOutOfStockCount() {
        String jpql = "SELECT COUNT(i) FROM Item i WHERE i.status = :status";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("status", StockStatus.OUT_OF_STOCK);
        return query.getSingleResult();
    }

    public Long getNearingStockCount() {
        String jpql = "SELECT COUNT(i) FROM Item i WHERE i.status = :status";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("status", StockStatus.NEARING_STOCK);
        return query.getSingleResult();
    }

    public List<Item> getTop5NearingStockItems() {
        String jpql = "SELECT i FROM Item i WHERE i.status = :status ORDER BY i.stockQuantity ASC";
        TypedQuery<Item> query = entityManager.createQuery(jpql, Item.class);
        query.setParameter("status", StockStatus.NEARING_STOCK);
        query.setMaxResults(5);
        return query.getResultList();
    }

    public List<Item> getAllNearingStockItems() {
        String jpql = "SELECT i FROM Item i WHERE i.status = :status ORDER BY i.stockQuantity ASC";
        TypedQuery<Item> query = entityManager.createQuery(jpql, Item.class);
        query.setParameter("status", StockStatus.NEARING_STOCK);
        return query.getResultList();
    }

    public List<Item> getTop5OutOfStockItems() {
        String jpql = "SELECT i FROM Item i WHERE i.status = :status ORDER BY i.updatedAt DESC";
        TypedQuery<Item> query = entityManager.createQuery(jpql, Item.class);
        query.setParameter("status", StockStatus.OUT_OF_STOCK);
        query.setMaxResults(5);
        return query.getResultList();
    }

    public List<Item> getAllOutOfStockItems() {
        String jpql = "SELECT i FROM Item i WHERE i.status = :status ORDER BY i.updatedAt DESC";
        TypedQuery<Item> query = entityManager.createQuery(jpql, Item.class);
        query.setParameter("status", StockStatus.OUT_OF_STOCK);
        return query.getResultList();
    }
}

