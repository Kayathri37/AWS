package com.AWS.Figma.Category.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CreateTypeRequest {
    @NotNull(message = "Brand ID is required")
    private Long brandId;

    @NotEmpty(message = "Type name is required")
    private String typeName;
}
