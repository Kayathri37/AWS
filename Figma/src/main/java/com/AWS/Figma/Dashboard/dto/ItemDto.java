package com.AWS.Figma.Dashboard.dto;

import com.AWS.Figma.Dashboard.entity.StockStatus;

public class ItemDto {
    private Long id;
    private String description;
    private Integer stockQuantity;
    private Integer minStockLevel;
    private StockStatus status;
    private String flavor;

    public ItemDto() {}

    // ✅ Corrected Constructor
    public ItemDto(Long id, String description, Integer stockQuantity, Integer minStockLevel, StockStatus status, String flavor) {
        this.id = id;
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.minStockLevel = minStockLevel;
        this.status = status;
        this.flavor = flavor;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFlavor() { return flavor; }
    public void setFlavor(String flavor) { this.flavor = flavor; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

    public Integer getMinStockLevel() { return minStockLevel; }
    public void setMinStockLevel(Integer minStockLevel) { this.minStockLevel = minStockLevel; }

    public StockStatus getStatus() { return status; }
    public void setStatus(StockStatus status) { this.status = status; }
}
