package com.AWS.Figma.Dashboard.DashboardController;

import com.AWS.Figma.Dashboard.DTO.*;
import com.AWS.Figma.Dashboard.DashboardService.InventoryDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DashboardController {

    @Autowired
    private InventoryDashboardService inventoryDashboardService;


    @GetMapping("/InventorySummary")
    public ResponseEntity<InventorySummaryDto> getInventorySummary(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        InventorySummaryDto summary = inventoryDashboardService.fetchInventorySummary();
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/top5 nearing stock")
    public ResponseEntity<List<NearingStockItemDto>> top5NearingStock(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<NearingStockItemDto> items = inventoryDashboardService.getTop5NearingStockItems();
        return ResponseEntity.ok(items);
    }

@GetMapping("/nearing stock/all")
public ResponseEntity<List<NearingStockItemDto>> allNearingStock(
        @RequestHeader(value = "Authorization", required = false) String authHeader) {

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    List<NearingStockItemDto> items = inventoryDashboardService.getAllNearingStockItems();
    return ResponseEntity.ok(items);
}

    @GetMapping("/top5 out of stock")
    public ResponseEntity<List<OutOfStockItemDto>> top5OutOfStock(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<OutOfStockItemDto> items = inventoryDashboardService.getTop5OutOfStockItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/out of stock/all")
    public ResponseEntity<List<OutOfStockItemDto>> allOutOfStock(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<OutOfStockItemDto> items = inventoryDashboardService.getAllOutOfStockItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/Salessummary")
    public ResponseEntity<MonthlySalesSummaryDto> getMonthlySalesSummary(
            @RequestParam int month,
            @RequestParam int year,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        MonthlySalesSummaryDto summary =
                inventoryDashboardService.getMonthlySalesSummary(month, year);

        return ResponseEntity.ok(summary);
    }
}



