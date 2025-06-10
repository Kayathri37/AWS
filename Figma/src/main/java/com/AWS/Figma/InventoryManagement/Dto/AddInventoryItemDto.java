package com.AWS.Figma.InventoryManagement.Dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AddInventoryItemDto {

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Type is required")
    private String type;

    @NotNull(message = "Sample Price is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal samplePrice;

    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;
    @Min(value = 0, message = "Stock threshold cannot be negative")
    private int stockThreshold;

    private String remark;
}
