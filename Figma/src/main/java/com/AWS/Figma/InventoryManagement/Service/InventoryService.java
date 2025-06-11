package com.AWS.Figma.InventoryManagement.Service;

import com.AWS.Figma.InventoryManagement.Dto.AddInventoryItemDto;
import com.AWS.Figma.InventoryManagement.Dto.EditInventoryItemDto;
import com.AWS.Figma.InventoryManagement.Dto.StockStatus;
import com.AWS.Figma.InventoryManagement.Entity.InventoryItem;
import com.AWS.Figma.InventoryManagement.Repo.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;
    private Query query;

    public InventoryItem addItem(AddInventoryItemDto dto) {
        InventoryItem item = new InventoryItem();

        // description = category / brand / type
        String description = dto.getCategory() + " / " + dto.getBrand() + " / " + dto.getType();
        item.setDescription(description);

        item.setQuantity(dto.getQuantity());
        item.setSamplePrice(dto.getSamplePrice());
        item.setRemark(dto.getRemark());

        // Set stock status
        if (dto.getQuantity() <= 0) {
            item.setStatus(StockStatus.OUT_OF_STOCK);
        }
            else if (dto.getQuantity()>=10) {

            item.setStatus(StockStatus.IN_STOCK);
        } else {
            item.setStatus(StockStatus.NEARING_STOCK);
        }

        return repository.save(item);
    }


    public boolean editItem(Long id, EditInventoryItemDto dto, String token) {
        Optional<InventoryItem> optionalItem = repository.findById(id);

        if (optionalItem.isEmpty()) {
            System.out.println(">> No item found with ID: " + id); // 🔍 LOG
            return false;
        }

        InventoryItem item = optionalItem.get();

        // Construct description properly
        String desc = dto.getCategory() + " / " + dto.getBrand() + " / " + dto.getType();
        item.setDescription(desc);

        item.setSamplePrice(dto.getSamplePrice());
        item.setQuantity(dto.getStockCount());
        item.setRemark(dto.getRemark());

        // Status update logic
        if (dto.getStockCount() <= 0) {
            item.setStatus(StockStatus.OUT_OF_STOCK);
        } else if (dto.getStockCount() <= dto.getStockThreshold()) {
            item.setStatus(StockStatus.NEARING_STOCK);
        } else {
            item.setStatus(StockStatus.IN_STOCK);
        }

        repository.save(item);

        System.out.println(">> Item updated successfully: " + item.getId());
        return true;
    }
    public InventoryItem getItemById(Long id) {
        return repository.findById(id).orElse(null);
    }
    public boolean addQuantity(Long id, int quantityToAdd) {
        Optional<InventoryItem> optionalItem = repository.findById(id);
        if (optionalItem.isEmpty()) {
            return false;
        }

        InventoryItem item = optionalItem.get();


        int updatedQuantity = item.getQuantity() + quantityToAdd;
        item.setQuantity(updatedQuantity);


        if (updatedQuantity <= 0) {
            item.setStatus(StockStatus.OUT_OF_STOCK);

        } else if (updatedQuantity>=10) {
            item.setStatus(StockStatus.IN_STOCK);

        } else {
            item.setStatus(StockStatus.NEARING_STOCK);
        }

        repository.save(item);
        return true;
    }
    public List<InventoryItem> searchByDescriptionPrefix(String prefix) {
        return repository.findByDescriptionStartingWithIgnoreCase(prefix);
    }




}
