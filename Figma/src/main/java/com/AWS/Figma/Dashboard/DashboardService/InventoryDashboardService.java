package com.AWS.Figma.Dashboard.DashboardService;

import com.AWS.Figma.Dashboard.DAO.InventoryDashboardDAO;
import com.AWS.Figma.Dashboard.DTO.InventorySummaryDto;
import com.AWS.Figma.Dashboard.DTO.NearingStockItemDto;
import com.AWS.Figma.Dashboard.DTO.OutOfStockItemDto;
import com.AWS.Figma.Dashboard.Facade.InventoryDashboardFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryDashboardService {

    private static final int NEARING_STOCK_THRESHOLD = 5;

    //private final InventoryDashboardDAO dao;
    @Autowired
    private InventoryDashboardFacade inventoryDashboardFacade;

    public InventorySummaryDto fetchInventorySummary() {
        InventorySummaryDto dto = new InventorySummaryDto();
        dto.setTotalItems(inventoryDashboardFacade.getTotalItemCount());
        dto.setNearingStockCount(
                inventoryDashboardFacade.getNearingStockCount(NEARING_STOCK_THRESHOLD));
        dto.setOutOfStockCount(inventoryDashboardFacade.getOutOfStockCount());
        return dto;

    }
    public List<NearingStockItemDto> getTop5NearingStockItems() {
        return inventoryDashboardFacade.viewTop5NearingStock();
    }
    public List<NearingStockItemDto> getAllNearingStockItems() {
        return inventoryDashboardFacade.getAllNearingStockItems();
    }
    public List<OutOfStockItemDto> getTop5OutOfStockItems() {
        return inventoryDashboardFacade.viewTop5OutOfStock();
    }
    public List<OutOfStockItemDto> getAllOutOfStockItems() {
        return inventoryDashboardFacade.viewAllOutOfStock();
    }
}

