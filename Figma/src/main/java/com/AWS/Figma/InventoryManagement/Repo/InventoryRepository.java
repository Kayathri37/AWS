package com.AWS.Figma.InventoryManagement.Repo;

import com.AWS.Figma.InventoryManagement.Entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
    Optional<InventoryItem> findById(Long id);
    List<InventoryItem> findByDescriptionStartingWithIgnoreCase(String prefix);

}
