package com.AWS.Figma.InventoryManagement.Facade;

import com.AWS.Figma.InventoryManagement.Entity.InventoryItem;
import com.AWS.Figma.InventoryManagement.Repo.InventoryRepository;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@Component
public class ValidationFacade  {

    @Autowired
    private InventoryRepository repository;
    @NotBlank
    private String description;

    @Min(0)
    private int quantity;

    @DecimalMin("0.0")
    private BigDecimal samplePrice;

    public InventoryItem createItem(InventoryItem item) {
        if (item.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        if (item.getSamplePrice() == null || item.getSamplePrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Sample price must be non-negative.");
        }

        return repository.save(item);
    }


}
