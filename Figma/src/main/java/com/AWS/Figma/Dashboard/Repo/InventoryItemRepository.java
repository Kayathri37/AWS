package com.AWS.Figma.Dashboard.Repo;

import com.AWS.Figma.InventoryManagement.Entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
}
