package com.AWS.Figma.visitormanagement.service;


import com.AWS.Figma.visitormanagement.dto.VisitorDto;

import java.util.List;

public interface VisitorService {
    VisitorDto addVisitor(VisitorDto dto);
    List<VisitorDto> getAllVisitors();
    List<VisitorDto> searchVisitorsByName(String name);

}

