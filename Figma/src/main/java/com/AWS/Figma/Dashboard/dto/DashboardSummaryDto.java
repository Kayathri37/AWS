package com.AWS.Figma.Dashboard.dto;

public class DashboardSummaryDto {
    private Long totalItems;
    private Long outOfStockItems;
    private Long nearingStockItems;

    public DashboardSummaryDto(Long totalItems, Long outOfStockItems, Long nearingStockItems) {
        this.totalItems = totalItems;
        this.outOfStockItems = outOfStockItems;
        this.nearingStockItems = nearingStockItems;
    }


    public Long getTotalItems() { return totalItems; }
    public void setTotalItems(Long totalItems) { this.totalItems = totalItems; }

    public Long getOutOfStockItems() { return outOfStockItems; }
    public void setOutOfStockItems(Long outOfStockItems) { this.outOfStockItems = outOfStockItems; }

    public Long getNearingStockItems() { return nearingStockItems; }
    public void setNearingStockItems(Long nearingStockItems) { this.nearingStockItems = nearingStockItems; }
}