package com.AWS.Figma.InventoryManagement.DAO;

import com.AWS.Figma.InventoryManagement.Entity.InventoryItem;
import com.AWS.Figma.InventoryManagement.query.InventoryQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InventoryDaoImpl implements InventoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<InventoryItem> findById(Long id) {
        try {
            InventoryItem item = (InventoryItem) entityManager.createNativeQuery(InventoryQuery.FIND_BY_ID, InventoryItem.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(item);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<InventoryItem> findByDescriptionPrefix(String prefix) {
        return entityManager.createNativeQuery(InventoryQuery.FIND_BY_DESCRIPTION_PREFIX, InventoryItem.class)
                .setParameter("prefix", prefix)
                .getResultList();
    }

    @Transactional
    @Override
    public InventoryItem saveItem(InventoryItem item) {
        if (item.getId() == null) {
            entityManager.persist(item);
            return item;
        } else {
            return entityManager.merge(item);
        }
    }
}
