package com.AWS.Figma.Category.Facade;

import com.AWS.Figma.Category.Dto.CreateTypeRequest;
import org.springframework.stereotype.Component;

@Component
public class TypeValidationFacade {

    public void validateCreateType(CreateTypeRequest request) {
        if (request.getBrandId() == null || request.getBrandId() <= 0) {
            throw new IllegalArgumentException("Brand ID is invalid");
        }

        if (request.getTypeName() == null || request.getTypeName().trim().isEmpty()) {
            throw new IllegalArgumentException("Type name is required");
        }
    }
}
