package com.AWS.Figma.Dashboard.service;

import com.AWS.Figma.Dashboard.dto.DashboardSummaryDto;
import com.AWS.Figma.Dashboard.dto.ItemDto;
import com.AWS.Figma.Dashboard.entity.Item;
import com.AWS.Figma.Dashboard.query.ItemQuery;
import com.AWS.Figma.Dashboard.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemQuery itemQuery;

    public DashboardSummaryDto getDashboardSummary() {
        Long totalItems = itemQuery.getTotalItemsCount();
        Long outOfStockItems = itemQuery.getOutOfStockCount();
        Long nearingStockItems = itemQuery.getNearingStockCount();

        return new DashboardSummaryDto(totalItems, outOfStockItems, nearingStockItems);
    }

    public List<ItemDto> getTop5NearingStockItems() {
        List<Item> items = itemQuery.getTop5NearingStockItems();
        return convertToDto(items);
    }

    public List<ItemDto> getAllNearingStockItems() {
        List<Item> items = itemQuery.getAllNearingStockItems();
        return convertToDto(items);
    }

    public List<ItemDto> getTop5OutOfStockItems() {
        List<Item> items = itemQuery.getTop5OutOfStockItems();
        return convertToDto(items);
    }

    public List<ItemDto> getAllOutOfStockItems() {
        List<Item> items = itemQuery.getAllOutOfStockItems();
        return convertToDto(items);
    }

    public ItemDto createItem(ItemDto itemDto) {
        Item item = new Item(itemDto.getDescription(), itemDto.getStockQuantity(), itemDto.getMinStockLevel());
        item.setFlavor(itemDto.getFlavor()); // ✅ Include this
        Item savedItem = itemRepository.save(item);
        return convertToDto(savedItem);
    }


    public ItemDto updateItem(Long id, ItemDto itemDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        item.setDescription(itemDto.getDescription());
        item.setStockQuantity(itemDto.getStockQuantity());
        item.setMinStockLevel(itemDto.getMinStockLevel());

        Item updatedItem = itemRepository.save(item);
        return convertToDto(updatedItem);
    }

    private List<ItemDto> convertToDto(List<Item> items) {
        return items.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ItemDto convertToDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getDescription(),
                item.getStockQuantity(),
                item.getMinStockLevel(),
                item.getStatus(),
                item.getFlavor() // ✅ Add this line to match the constructor
        );
    }
}


