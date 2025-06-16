package com.AWS.Figma.Dashboard.controller;

import com.AWS.Figma.Dashboard.dto.DailySalesDto;
import com.AWS.Figma.Dashboard.dto.MonthlySalesSummaryDto;
import com.AWS.Figma.Dashboard.dto.SalesDto;
import com.AWS.Figma.Dashboard.exception.ItemNotFoundException;
import com.AWS.Figma.Dashboard.service.SalesService;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "*")
public class SalesController {

    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/dashboard/daily/{year}/{month}")
    public ResponseEntity<?> getDailySalesChart(@PathVariable int year, @PathVariable int month) {
        if (year < 1900 || year > 2100 || month < 1 || month > 12)
            return ResponseEntity.badRequest().body("Invalid year or month.");

        try {
            List<DailySalesDto> dailySales = salesService.getDailySalesForMonth(year, month);
            return ResponseEntity.ok(dailySales);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving daily sales data");
        }
    }

    @GetMapping("/dashboard/daily/current")
    public ResponseEntity<?> getCurrentMonthDailySales() {
        try {
            YearMonth now = YearMonth.now();
            return ResponseEntity.ok(salesService.getDailySalesForMonth(now.getYear(), now.getMonthValue()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving current month daily sales");
        }
    }

    @GetMapping("/dashboard/summary/{year}/{month}")
    public ResponseEntity<?> getMonthlySummary(@PathVariable int year, @PathVariable int month) {
        if (year < 1900 || year > 2100 || month < 1 || month > 12)
            return ResponseEntity.badRequest().body("Invalid year or month.");

        try {
            MonthlySalesSummaryDto summary = salesService.getMonthlySalesSummary(year, month);
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving monthly summary");
        }
    }

    @GetMapping("/dashboard/summary/current")
    public ResponseEntity<?> getCurrentMonthSummary() {
        try {
            YearMonth now = YearMonth.now();
            return ResponseEntity.ok(salesService.getMonthlySalesSummary(now.getYear(), now.getMonthValue()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving current month summary");
        }
    }

    @PostMapping
    public ResponseEntity<?> createSale(@Valid @RequestBody SalesDto salesDto) {
        try {
            SalesDto created = salesService.createSale(salesDto);
            return ResponseEntity.status(201).body(created);
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error creating sale");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSales() {
        try {
            return ResponseEntity.ok(salesService.getAllSales());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving all sales");
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<?> getSalesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        if (startDate.isAfter(endDate))
            return ResponseEntity.badRequest().body("Start date cannot be after end date");

        try {
            return ResponseEntity.ok(salesService.getSalesByDateRange(startDate, endDate));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving sales by date range");
        }
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<?> getSalesByItem(@PathVariable Long itemId) {
        if (itemId <= 0)
            return ResponseEntity.badRequest().body("Invalid item ID");

        try {
            return ResponseEntity.ok(salesService.getSalesByItem(itemId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving sales by item");
        }
    }
}
