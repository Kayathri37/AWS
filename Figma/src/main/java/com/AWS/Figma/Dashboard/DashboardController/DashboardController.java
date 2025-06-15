package com.AWS.Figma.Dashboard.DashboardController;

import com.AWS.Figma.Dashboard.DTO.InventoryDashboardDto;
import com.AWS.Figma.Dashboard.DTO.NearingStockCountDto;
import com.AWS.Figma.Dashboard.Facade.InventoryDashboardFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DashboardController {

    @Autowired
    private InventoryDashboardFacade facade;

    @GetMapping("/total items")
    public ResponseEntity<InventoryDashboardDto> getTotalItems() {
        InventoryDashboardDto dto = facade.fetchTotalItemCount();
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/nearing stock count")
    public ResponseEntity<NearingStockCountDto> showNearingStockCount() {
        return ResponseEntity.ok(facade.viewNearingStockCount());
    }
}

