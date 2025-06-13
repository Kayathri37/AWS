package com.AWS.Figma.InventoryManagement.DAO;

import com.AWS.Figma.InventoryManagement.Entity.InventoryItem;

import java.util.List;
import java.util.Optional;

public interface InventoryDao {
    Optional<InventoryItem> findById(Long id);
    List<InventoryItem> findByDescriptionPrefix(String prefix);
    InventoryItem saveItem(InventoryItem item);
}
