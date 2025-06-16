package com.AWS.Figma.Dashboard.DashboardController;

import com.AWS.Figma.Dashboard.DTO.InventorySummaryDto;
import com.AWS.Figma.Dashboard.DTO.NearingStockItemDto;
import com.AWS.Figma.Dashboard.Facade.InventoryDashboardFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DashboardController {

    private final InventoryDashboardFacade facade;

    @GetMapping("/InventorySummary")
    public ResponseEntity<?> InventorySummary(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Missing or malformed Authorization header");
        }

        InventorySummaryDto summary = facade.getInventorySummary();

        return ResponseEntity.ok(summary);
    }
    @GetMapping("/top5 nearing stock")
    public ResponseEntity<List<NearingStockItemDto>> top5NearingStock(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return ResponseEntity.ok(facade.viewTop5NearingStock());
    }

}

