package com.AWS.Figma.Dashboard.DashboardService;

import com.AWS.Figma.Dashboard.DAO.InventoryDashboardDAO;
import com.AWS.Figma.Dashboard.DTO.InventorySummaryDto;
import com.AWS.Figma.Dashboard.DTO.NearingStockItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryDashboardService {

    private static final int NEARING_STOCK_THRESHOLD = 5;

    private final InventoryDashboardDAO dao;

    public InventorySummaryDto fetchInventorySummary() {
        InventorySummaryDto dto = new InventorySummaryDto();
        dto.setTotalItems(dao.getTotalItemCount());
        dto.setNearingStockCount(dao.getNearingStockCount(NEARING_STOCK_THRESHOLD));
        dto.setOutOfStockCount(dao.getOutOfStockCount());
        return dto;
    }
    public List<NearingStockItemDto> getTop5NearingStockItems() {
        return dao.getTop5NearingStock();
    }
}

