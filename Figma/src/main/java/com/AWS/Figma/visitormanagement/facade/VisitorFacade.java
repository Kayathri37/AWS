package com.AWS.Figma.visitormanagement.facade;


import com.AWS.Figma.visitormanagement.dto.VisitorDto;
import com.AWS.Figma.visitormanagement.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VisitorFacade {

    @Autowired
    private VisitorService visitorService;

    public VisitorDto addVisitor(VisitorDto dto) {
        return visitorService.addVisitor(dto);
    }

    public List<VisitorDto> getAllVisitors() {
        return visitorService.getAllVisitors();
    }
    public List<VisitorDto> searchVisitorsByName(String name) {
        return visitorService.searchVisitorsByName(name);
    }

}


