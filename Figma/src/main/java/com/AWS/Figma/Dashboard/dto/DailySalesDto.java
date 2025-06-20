package com.AWS.Figma.Dashboard.dto;

import java.time.LocalDate;

public class DailySalesDto {
    private LocalDate date;
    private Integer totalItemsSold;
    private Double totalAmount;
    private Integer dayOfMonth;

    public DailySalesDto() {}

    public DailySalesDto(LocalDate date, Integer totalItemsSold, Double totalAmount) {
        this.date = date;
        this.totalItemsSold = totalItemsSold;
        this.totalAmount = totalAmount;
        this.dayOfMonth = date.getDayOfMonth();
    }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { 
        this.date = date; 
        this.dayOfMonth = date != null ? date.getDayOfMonth() : null;
    }

    public Integer getTotalItemsSold() { return totalItemsSold; }
    public void setTotalItemsSold(Integer totalItemsSold) { this.totalItemsSold = totalItemsSold; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public Integer getDayOfMonth() { return dayOfMonth; }
    public void setDayOfMonth(Integer dayOfMonth) { this.dayOfMonth = dayOfMonth; }
}
