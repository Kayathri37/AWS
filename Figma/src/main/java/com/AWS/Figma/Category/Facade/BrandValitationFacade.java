package com.AWS.Figma.Category.Facade;

import com.AWS.Figma.Category.Entity.Brand;
import com.AWS.Figma.Category.Repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandValitationFacade {
    @Autowired
    private BrandRepository brandRepo;


    public Brand validateAndGetBrand(Long brandId, Long categoryId) {
        Brand brand = brandRepo.findById(brandId)
                .orElseThrow(() -> new IllegalArgumentException("Brand not found"));

        if (!brand.getCategory().getId().equals(categoryId)) {
            throw new IllegalArgumentException("Brand does not belong to the specified category");
        }

        return brand;
    }
}
