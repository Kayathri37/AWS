package com.AWS.Figma.Dashboard.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class MonthlySalesSummaryDto {
    private String month;
    private int year;
    private int totalSales;
    private List<DailySalesDto> dailySales;
}