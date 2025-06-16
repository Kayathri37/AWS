package com.AWS.Figma.Dashboard.service;

import com.AWS.Figma.Dashboard.dto.*;


import java.time.LocalDate;
import java.util.List;

public interface SalesServiceImpl {
    SalesDto createSale(SalesDto dto);
    List<SalesDto> getAllSales();
    List<DailySalesDto> getDailySalesForMonth(int year, int month);
    MonthlySalesSummaryDto getMonthlySalesSummary(int year, int month);
    List<SalesDto> getSalesByDateRange(LocalDate startDate, LocalDate endDate);
    List<SalesDto> getSalesByItem(Long itemId);
}
