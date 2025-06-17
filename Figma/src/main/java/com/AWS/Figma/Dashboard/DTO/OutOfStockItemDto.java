package com.AWS.Figma.Dashboard.DTO;

import com.AWS.Figma.InventoryManagement.Dto.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutOfStockItemDto {
    private Long id;
    private String description;
    private StockStatus status;
}
