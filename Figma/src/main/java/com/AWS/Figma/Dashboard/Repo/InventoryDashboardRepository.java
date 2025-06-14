package com.AWS.Figma.Dashboard.Repo;

import com.AWS.Figma.InventoryManagement.Entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryDashboardRepository extends JpaRepository<InventoryItem, Long> {
}
