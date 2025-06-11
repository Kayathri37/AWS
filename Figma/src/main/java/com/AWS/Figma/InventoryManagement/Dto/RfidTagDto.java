

package com.AWS.Figma.InventoryManagement.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RfidTagDto {
    private String description;
    private String tagCode;

    public RfidTagDto(String description, String tagCode) {
        this.description = description;
        this.tagCode = tagCode;
    }
}