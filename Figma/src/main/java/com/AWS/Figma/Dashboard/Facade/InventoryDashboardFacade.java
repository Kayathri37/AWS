package com.AWS.Figma.Dashboard.Facade;

import com.AWS.Figma.Dashboard.DAO.InventoryDashboardDAO;
import com.AWS.Figma.Dashboard.DTO.NearingStockItemDto;
import com.AWS.Figma.Dashboard.DTO.OutOfStockItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryDashboardFacade {

    @Autowired
   private InventoryDashboardDAO dao;

    public int getTotalItemCount() {
        return dao.getTotalItemCount();
    }
    public int getNearingStockCount(int threshold) {
        return dao.getNearingStockCount(threshold);
    }

    public int getOutOfStockCount() {
        return dao.getOutOfStockCount();
    }
    public List<NearingStockItemDto> viewTop5NearingStock() {
        return dao.getTop5NearingStock();
    }
    public List<NearingStockItemDto> getAllNearingStockItems() {
        return dao.getAllNearingStockItems();
    }
    public List<OutOfStockItemDto> viewTop5OutOfStock() {
        return dao.getTop5OutOfStockItems();
    }
    public List<OutOfStockItemDto> viewAllOutOfStock() {
        return dao.getAllOutOfStockItems();
    }
}
