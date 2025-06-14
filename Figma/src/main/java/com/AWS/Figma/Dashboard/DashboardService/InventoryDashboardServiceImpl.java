package com.AWS.Figma.Dashboard.DashboardService;

import com.AWS.Figma.Dashboard.DAO.InventoryDashboardDao;
import com.AWS.Figma.Dashboard.DTO.InventoryDashboardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryDashboardServiceImpl implements InventoryDashboardService {

    @Autowired
    private InventoryDashboardDao dao;

    @Override
    public InventoryDashboardDto getTotalItemCount() {
        long count = dao.getTotalItemCount();
        return new InventoryDashboardDto(count);
    }
}
