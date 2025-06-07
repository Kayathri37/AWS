package com.AWS.Figma.InventoryManagement.Service;

import com.AWS.Figma.InventoryManagement.Entity.InventoryItem;
import com.AWS.Figma.InventoryManagement.Repo.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository repository;

    public InventoryItem addItem(InventoryItem item) {
        return repository.save(item);
    }
}

