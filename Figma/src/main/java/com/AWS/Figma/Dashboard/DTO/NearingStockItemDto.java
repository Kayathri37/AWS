package com.AWS.Figma.Dashboard.DTO;

import com.AWS.Figma.InventoryManagement.Dto.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class NearingStockItemDto {
    private Long id;
    private String description;
    private StockStatus status;
}
