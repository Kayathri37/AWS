package com.AWS.Figma.Dashboard.DAO;

import com.AWS.Figma.Dashboard.DTO.NearingStockItemDto;
import com.AWS.Figma.Dashboard.DTO.OutOfStockItemDto;
import com.AWS.Figma.Dashboard.Query.InventoryDashboardQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InventoryDashboardDAO {

    @Autowired
    private InventoryDashboardQuery query;

    public int getTotalItemCount() {
        return query.fetchTotalItems();
    }

    public int getNearingStockCount(int nearingStockThreshold) {
        return query.fetchNearingStockItems();
    }

    public int getOutOfStockCount() {
        return query.fetchOutOfStockItems();
    }
    public List<NearingStockItemDto> getTop5NearingStock() {
        return query.fetchTop5NearingStockItems();
    }
    public List<NearingStockItemDto> getAllNearingStockItems() {
        return query.fetchAllNearingStockItems();
    }
    public List<OutOfStockItemDto> getTop5OutOfStockItems() {
        return query.fetchTop5OutOfStockItems();
    }
    public List<OutOfStockItemDto> getAllOutOfStockItems() {
        return query.fetchAllOutOfStockItems();
    }
}
