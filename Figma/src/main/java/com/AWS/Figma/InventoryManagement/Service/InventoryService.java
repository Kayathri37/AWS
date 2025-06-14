package com.AWS.Figma.InventoryManagement.Service;

import com.AWS.Figma.InventoryManagement.DAO.InventoryDao;
import com.AWS.Figma.InventoryManagement.Dto.AddInventoryItemDto;
import com.AWS.Figma.InventoryManagement.Dto.EditInventoryItemDto;
import com.AWS.Figma.InventoryManagement.Dto.RfidTagDto;
import com.AWS.Figma.InventoryManagement.Dto.StockStatus;
import com.AWS.Figma.InventoryManagement.Entity.InventoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryDao inventoryDao;

    private Query query;

    public InventoryItem addItem(AddInventoryItemDto dto) {
        InventoryItem item = new InventoryItem();

        String description = dto.getCategory() + " / " + dto.getBrand() + " / " + dto.getType();
        item.setDescription(description);

        item.setQuantity(dto.getQuantity());
        item.setSamplePrice(dto.getSamplePrice());
        item.setRemark(dto.getRemark());

        if (dto.getQuantity() <= 0) {
            item.setStatus(StockStatus.OUT_OF_STOCK);
        } else if (dto.getQuantity() >= 10) {
            item.setStatus(StockStatus.IN_STOCK);
        } else {
            item.setStatus(StockStatus.NEARING_STOCK);
        }

        return inventoryDao.saveItem(item);
    }

    public boolean editItem(Long id, EditInventoryItemDto dto, String token) {
        Optional<InventoryItem> optionalItem = inventoryDao.findById(id);

        if (optionalItem.isEmpty()) {
            System.out.println(">> No item found with ID: " + id);
            return false;
        }

        InventoryItem item = optionalItem.get();

        String desc = dto.getCategory() + " / " + dto.getBrand() + " / " + dto.getType();
        item.setDescription(desc);
        item.setSamplePrice(dto.getSamplePrice());
        item.setQuantity(dto.getStockCount());
        item.setRemark(dto.getRemark());

        if (dto.getStockCount() <= 0) {
            item.setStatus(StockStatus.OUT_OF_STOCK);
        } else if (dto.getStockCount() <= dto.getStockThreshold()) {
            item.setStatus(StockStatus.NEARING_STOCK);
        } else {
            item.setStatus(StockStatus.IN_STOCK);
        }

        inventoryDao.saveItem(item);
        System.out.println(">> Item updated successfully: " + item.getId());
        return true;
    }

    public InventoryItem getItemById(Long id) {
        return inventoryDao.findById(id).orElse(null);
    }

    public boolean addQuantity(Long id, int quantityToAdd) {
        Optional<InventoryItem> optionalItem = inventoryDao.findById(id);
        if (optionalItem.isEmpty()) {
            return false;
        }

        InventoryItem item = optionalItem.get();

        int updatedQuantity = item.getQuantity() + quantityToAdd;
        item.setQuantity(updatedQuantity);

        if (updatedQuantity <= 0) {
            item.setStatus(StockStatus.OUT_OF_STOCK);
        } else if (updatedQuantity >= 10) {
            item.setStatus(StockStatus.IN_STOCK);
        } else {
            item.setStatus(StockStatus.NEARING_STOCK);
        }

        inventoryDao.saveItem(item);
        return true;
    }

    public List<InventoryItem> searchByDescriptionPrefix(String prefix) {
        return inventoryDao.findByDescriptionPrefix(prefix);
    }

    public List<RfidTagDto> generateRfidTags(Long itemId) {
        Optional<InventoryItem> optionalItem = inventoryDao.findById(itemId);
        if (optionalItem.isEmpty()) {
            throw new IllegalArgumentException("Item not found with ID: " + itemId);
        }

        InventoryItem item = optionalItem.get();
        String description = item.getDescription();
        int quantity = item.getQuantity();

        List<RfidTagDto> tags = new ArrayList<>();

        long baseCode = 1000000000000L + item.getId() * 100;
        for (int i = 0; i < quantity; i++) {
            String tagCode = String.format("%016d", baseCode + i);
            tags.add(new RfidTagDto(description, tagCode));
        }

        return tags;
    }
}
