package com.AWS.Figma.Dashboard.DashboardService;

import com.AWS.Figma.Dashboard.DAO.InventoryDashboardDao;
import com.AWS.Figma.Dashboard.DAO.NearingStockDao;
import com.AWS.Figma.Dashboard.DTO.InventoryDashboardDto;
import com.AWS.Figma.Dashboard.DTO.NearingStockCountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryDashboardServiceImpl implements InventoryDashboardService {

    @Autowired
    private InventoryDashboardDao dao;
    @Autowired
    private NearingStockDao nearingStockDao;

    @Override
    public InventoryDashboardDto getTotalItemCount() {
        long count = dao.getTotalItemCount();
        return new InventoryDashboardDto(count);
    }
   // @Override
    public NearingStockCountDto getNearingStockCount() {
        long count = nearingStockDao.getNearingStockCount();
        return new NearingStockCountDto(count);
    }
}

