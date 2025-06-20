package com.AWS.Figma.visitormanagement.util;

import com.AWS.Figma.visitormanagement.dto.VisitorDto;
import com.AWS.Figma.visitormanagement.entity.Visitor;

public class VisitorMapper {

    public static VisitorDto toDto(Visitor entity) {
        return new VisitorDto(entity.getName(), entity.getOrganisation(), entity.getRfiId());
    }



    public static Visitor toEntity(VisitorDto dto) {
        Visitor visitor = new Visitor();
        visitor.setName(dto.getName());
        visitor.setOrganisation(dto.getOrganisation());
        return visitor;
    }
}
