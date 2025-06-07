package com.AWS.Figma.InventoryManagement.Entity;

import com.AWS.Figma.InventoryManagement.Dto.StockStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "inventory_items")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private int quantity;
    private BigDecimal samplePrice;

    @Enumerated(EnumType.STRING)
    private StockStatus status;

    private String remark;
}
