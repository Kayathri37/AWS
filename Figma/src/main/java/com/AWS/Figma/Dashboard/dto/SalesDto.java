package com.AWS.Figma.Dashboard.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class SalesDto {
    private Long id;

    @NotNull(message = "Sale date is required")
    private LocalDate saleDate;

    @NotNull(message = "Items sold is required")
    @Min(value = 1, message = "Items sold must be at least 1")
    private Integer itemsSold;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total amount must be positive")
    private Double totalAmount;

    @NotNull(message = "Item ID is required")
    private Long itemId;

    private String itemDescription; // For response

    public SalesDto() {}

    public SalesDto(Long id, LocalDate saleDate, Integer itemsSold, Double totalAmount, Long itemId, String itemDescription) {
        this.id = id;
        this.saleDate = saleDate;
        this.itemsSold = itemsSold;
        this.totalAmount = totalAmount;
        this.itemId = itemId;
        this.itemDescription = itemDescription;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getSaleDate() { return saleDate; }
    public void setSaleDate(LocalDate saleDate) { this.saleDate = saleDate; }

    public Integer getItemsSold() { return itemsSold; }
    public void setItemsSold(Integer itemsSold) { this.itemsSold = itemsSold; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public String getItemDescription() { return itemDescription; }
    public void setItemDescription(String itemDescription) { this.itemDescription = itemDescription; }
}
