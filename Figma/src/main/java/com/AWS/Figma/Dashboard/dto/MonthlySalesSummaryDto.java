package com.AWS.Figma.Dashboard.dto;

public class MonthlySalesSummaryDto {
    private Integer totalItemsSold;
    private Double totalRevenue;
    private Integer totalTransactions;
    private String month;
    private Integer year;

    public MonthlySalesSummaryDto() {}

    public MonthlySalesSummaryDto(Integer totalItemsSold, Double totalRevenue, Integer totalTransactions, String month, Integer year) {
        this.totalItemsSold = totalItemsSold;
        this.totalRevenue = totalRevenue;
        this.totalTransactions = totalTransactions;
        this.month = month;
        this.year = year;
    }

    // Getters and Setters
    public Integer getTotalItemsSold() { return totalItemsSold; }
    public void setTotalItemsSold(Integer totalItemsSold) { this.totalItemsSold = totalItemsSold; }

    public Double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(Double totalRevenue) { this.totalRevenue = totalRevenue; }

    public Integer getTotalTransactions() { return totalTransactions; }
    public void setTotalTransactions(Integer totalTransactions) { this.totalTransactions = totalTransactions; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
}