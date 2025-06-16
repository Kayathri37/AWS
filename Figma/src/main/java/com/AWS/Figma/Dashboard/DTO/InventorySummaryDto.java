package com.AWS.Figma.Dashboard.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventorySummaryDto {
    private int totalItems;
    private int nearingStockCount;
    private int outOfStockCount;
}
