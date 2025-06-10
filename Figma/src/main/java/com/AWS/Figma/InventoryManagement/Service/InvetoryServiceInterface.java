package com.AWS.Figma.InventoryManagement.Service;

import com.AWS.Figma.InventoryManagement.Dto.EditInventoryItemDto;

public interface InvetoryServiceInterface {
        boolean editItem(Long id, EditInventoryItemDto dto, String token);
    }

