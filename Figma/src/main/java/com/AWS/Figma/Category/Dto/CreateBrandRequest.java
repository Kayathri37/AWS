package com.AWS.Figma.Category.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBrandRequest {
    private Long categoryId;
    private String brandName;
}
