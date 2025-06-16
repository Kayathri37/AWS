package com.AWS.Figma.Dashboard.Facade;

import com.AWS.Figma.Dashboard.DTO.InventorySummaryDto;
import com.AWS.Figma.Dashboard.DashboardService.InventoryDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryDashboardFacade {

    @Autowired
    private InventoryDashboardService service;

    public InventorySummaryDto getInventorySummary() {
        return service.fetchInventorySummary();
    }
}
