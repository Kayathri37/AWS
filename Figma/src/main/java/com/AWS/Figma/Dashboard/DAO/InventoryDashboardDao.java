package com.AWS.Figma.Dashboard.DAO;

import com.AWS.Figma.Dashboard.Query.InventoryDashboardQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryDashboardDao {

    @Autowired
    private InventoryDashboardQuery dashboardQuery;

    public long getTotalItemCount() {
        return dashboardQuery.getTotalItemCount();
    }
}
