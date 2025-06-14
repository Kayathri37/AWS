package com.AWS.Figma.Dashboard.Facade;

import com.AWS.Figma.Dashboard.DTO.InventoryDashboardDto;
import com.AWS.Figma.Dashboard.DashboardService.InventoryDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryDashboardFacade {

    @Autowired
    private InventoryDashboardService service;

    public InventoryDashboardDto fetchTotalItemCount() {
        return service.getTotalItemCount();
    }
}
