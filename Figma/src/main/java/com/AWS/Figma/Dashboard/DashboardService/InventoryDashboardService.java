package com.AWS.Figma.Dashboard.DashboardService;

import com.AWS.Figma.Dashboard.DAO.InventoryDashboardDAO;
import com.AWS.Figma.Dashboard.DTO.*;
import com.AWS.Figma.Dashboard.Facade.InventoryDashboardFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class InventoryDashboardService {

    private static final int NEARING_STOCK_THRESHOLD = 5;

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
    public MonthlySalesSummaryDto getMonthlySalesSummary(int month, int year) {

        List<DailySalesDto> raw = inventoryDashboardFacade.getDailySales(month, year);

        Map<Integer, Integer> salesMap = raw.stream()
                .collect(Collectors.toMap(DailySalesDto::getDay,
                        DailySalesDto::getItemsSold));

        int daysInMonth = YearMonth.of(year, month).lengthOfMonth();

        List<DailySalesDto> full = IntStream.rangeClosed(1, daysInMonth)
                .mapToObj(d -> new DailySalesDto(d, salesMap.getOrDefault(d, 0)))
                .collect(Collectors.toList());

        int total = full.stream().mapToInt(DailySalesDto::getItemsSold).sum();

        String monthName = Month.of(month).name().substring(0,1).toUpperCase()
                + Month.of(month).name().substring(1).toLowerCase();

        MonthlySalesSummaryDto dto = new MonthlySalesSummaryDto();
        dto.setMonth(monthName);
        dto.setYear(year);
        dto.setTotalSales(total);
        dto.setDailySales(full);

        return dto;
    }



}

