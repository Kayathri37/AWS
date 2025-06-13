package com.AWS.Figma.Category.Service;


import com.AWS.Figma.Category.DAO.TypeDao;
import com.AWS.Figma.Category.Dto.CreateTypeRequest;
import com.AWS.Figma.Category.Entity.Brand;
import com.AWS.Figma.Category.Entity.Type;
import com.AWS.Figma.Category.Facade.TypeValidationFacade;
import com.AWS.Figma.Category.Repository.BrandRepository;
import com.AWS.Figma.Category.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private TypeValidationFacade validationFacade;

    public Type createType(CreateTypeRequest request) {
        validationFacade.validateCreateType(request);

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new IllegalArgumentException("Brand not found"));

        Type type = new Type();
        type.setBrand(brand);
        type.setName(request.getTypeName());

        return typeRepository.save(type);
    }
}
