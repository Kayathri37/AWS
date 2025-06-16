package com.AWS.Figma.Dashboard.service;

import com.AWS.Figma.Dashboard.dao.ItemDao;
import com.AWS.Figma.Dashboard.dao.SalesDao;
import com.AWS.Figma.Dashboard.dto.DailySalesDto;
import com.AWS.Figma.Dashboard.dto.MonthlySalesSummaryDto;
import com.AWS.Figma.Dashboard.dto.SalesDto;
import com.AWS.Figma.Dashboard.entity.Item;
import com.AWS.Figma.Dashboard.entity.Sales;

import com.AWS.Figma.Dashboard.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Transactional
public class SalesService {

    @Autowired
    private SalesDao salesDao;

    @Autowired
    private ItemDao itemDao;

    public SalesDto createSale(SalesDto salesDto) {
        validateSalesDto(salesDto);

        Item item = itemDao.findById(salesDto.getItemId());
        if (item == null) {
            throw new ItemNotFoundException("Item not found with id: " + salesDto.getItemId());
        }

        // Check if enough stock is available
        if (item.getStockQuantity() < salesDto.getItemsSold()) {
            throw new IllegalArgumentException("Not enough stock available. Current stock: " + item.getStockQuantity());
        }

        // Create sales record
        Sales sales = new Sales();
        sales.setSaleDate(salesDto.getSaleDate());
        sales.setItemsSold(salesDto.getItemsSold());
        sales.setTotalAmount(salesDto.getTotalAmount());
        sales.setItem(item);

        Sales savedSales = salesDao.save(sales);

        // Update item stock
        item.setStockQuantity(item.getStockQuantity() - salesDto.getItemsSold());
        itemDao.save(item);

        return convertToDto(savedSales);
    }

    @Transactional(readOnly = true)
    public List<DailySalesDto> getDailySalesForMonth(int year, int month) {
        // Get actual sales data
        List<DailySalesDto> actualSales = salesDao.getDailySalesForMonth(year, month);

        // Create a complete list for all days in the month
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();

        List<DailySalesDto> completeSales = new ArrayList<>();

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = LocalDate.of(year, month, day);

            // Find actual sales for this day
            DailySalesDto dailySales = actualSales.stream()
                    .filter(s -> s.getDate().equals(date))
                    .findFirst()
                    .orElse(new DailySalesDto(date, 0, 0.0));

            completeSales.add(dailySales);
        }

        return completeSales;
    }

    @Transactional(readOnly = true)
    public MonthlySalesSummaryDto getMonthlySalesSummary(int year, int month) {
        Integer totalItemsSold = salesDao.getTotalItemsSoldForMonth(year, month);
        Double totalRevenue = salesDao.getTotalRevenueForMonth(year, month);
        Long totalTransactions = salesDao.getTotalTransactionsForMonth(year, month);

        String monthName = YearMonth.of(year, month)
                .getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        return new MonthlySalesSummaryDto(
                totalItemsSold,
                totalRevenue,
                totalTransactions.intValue(),
                monthName,
                year
        );
    }

    @Transactional(readOnly = true)
    public List<SalesDto> getAllSales() {
        List<Sales> sales = salesDao.findAll();
        return sales.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SalesDto> getSalesByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Sales> sales = salesDao.findByDateRange(startDate, endDate);
        return sales.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SalesDto> getSalesByItem(Long itemId) {
        List<Sales> sales = salesDao.findByItemId(itemId);
        return sales.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private void validateSalesDto(SalesDto salesDto) {
        if (salesDto.getSaleDate() == null) {
            throw new IllegalArgumentException("Sale date cannot be null");
        }
        if (salesDto.getItemsSold() == null || salesDto.getItemsSold() <= 0) {
            throw new IllegalArgumentException("Items sold must be greater than 0");
        }
        if (salesDto.getTotalAmount() == null || salesDto.getTotalAmount() <= 0) {
            throw new IllegalArgumentException("Total amount must be greater than 0");
        }
        if (salesDto.getItemId() == null) {
            throw new IllegalArgumentException("Item ID cannot be null");
        }
    }

    private SalesDto convertToDto(Sales sales) {
        return new SalesDto(
                sales.getId(),
                sales.getSaleDate(),
                sales.getItemsSold(),
                sales.getTotalAmount(),
                sales.getItem().getId(),
                sales.getItem().getDescription()
        );
    }
}