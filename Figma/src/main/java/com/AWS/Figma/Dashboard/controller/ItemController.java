package com.AWS.Figma.Dashboard.controller;

import com.AWS.Figma.Dashboard.dto.DashboardSummaryDto;
import com.AWS.Figma.Dashboard.dto.ItemDto;
import com.AWS.Figma.Dashboard.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/dashboard/summary")
    public ResponseEntity<DashboardSummaryDto> getDashboardSummary() {
        DashboardSummaryDto summary = itemService.getDashboardSummary();
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/nearing-stock/top5")
    public ResponseEntity<List<ItemDto>> getTop5NearingStockItems() {
        List<ItemDto> items = itemService.getTop5NearingStockItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/nearing-stock/all")
    public ResponseEntity<List<ItemDto>> getAllNearingStockItems() {
        List<ItemDto> items = itemService.getAllNearingStockItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/out-of-stock/top5")
    public ResponseEntity<List<ItemDto>> getTop5OutOfStockItems() {
        List<ItemDto> items = itemService.getTop5OutOfStockItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/out-of-stock/all")
    public ResponseEntity<List<ItemDto>> getAllOutOfStockItems() {
        List<ItemDto> items = itemService.getAllOutOfStockItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) {
        ItemDto createdItem = itemService.createItem(itemDto);
        return ResponseEntity.ok(createdItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        ItemDto updatedItem = itemService.updateItem(id, itemDto);
        return ResponseEntity.ok(updatedItem);
    }
}
