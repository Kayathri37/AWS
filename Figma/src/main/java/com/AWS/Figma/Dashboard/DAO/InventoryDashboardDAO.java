package com.AWS.Figma.Dashboard.DAO;

import com.AWS.Figma.Dashboard.Query.InventoryDashboardQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
