package com.AWS.Figma.category.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequestDto {
    private String categoryName;
    private String type;
    private String brand;
}
