package com.AWS.Figma.Category.Facade;

import com.AWS.Figma.Category.Dto.CreateTypeRequest;
import com.AWS.Figma.Category.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypeValidationFacade {
    @Autowired
    private CategoryRepository categoryRepository;

    public void validateCreateType(CreateTypeRequest request) {
        if (request.getBrandId() == null || request.getBrandId() <= 0) {
            throw new IllegalArgumentException("Brand ID is invalid");
        }

        if (request.getTypeName() == null || request.getTypeName().trim().isEmpty()) {
            throw new IllegalArgumentException("Type name is required");
        }
    }

}
