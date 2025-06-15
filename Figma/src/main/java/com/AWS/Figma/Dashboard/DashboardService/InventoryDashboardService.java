package com.AWS.Figma.Dashboard.DashboardService;


import com.AWS.Figma.Dashboard.DTO.InventoryDashboardDto;
import com.AWS.Figma.Dashboard.DTO.NearingStockCountDto;

public interface InventoryDashboardService {
    InventoryDashboardDto getTotalItemCount();
    NearingStockCountDto getNearingStockCount();

}
