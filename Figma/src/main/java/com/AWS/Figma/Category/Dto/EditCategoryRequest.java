package com.AWS.Figma.Category.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditCategoryRequest {

    @NotBlank(message = "Category name cannot be empty")
    private String name;
}
