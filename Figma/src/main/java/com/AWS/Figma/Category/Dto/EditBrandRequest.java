package com.AWS.Figma.Category.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditBrandRequest {
    private Long brandId;
    private String brandName;
}
