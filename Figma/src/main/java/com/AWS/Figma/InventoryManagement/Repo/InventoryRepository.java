package com.AWS.Figma.InventoryManagement.Repo;

import com.AWS.Figma.InventoryManagement.Entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
}
