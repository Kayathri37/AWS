package com.AWS.Figma.visitormanagement.controller;

import com.AWS.Figma.visitormanagement.dto.VisitorDto;
import com.AWS.Figma.visitormanagement.facade.VisitorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitors")
@CrossOrigin(origins = "*")
public class VisitorController {

    @Autowired
    private VisitorFacade visitorFacade;

    @PostMapping
    public VisitorDto addVisitor(@RequestBody VisitorDto dto) {
        return visitorFacade.addVisitor(dto);
    }

    @GetMapping
    public List<VisitorDto> getAllVisitors() {
        return visitorFacade.getAllVisitors();
    }
    @GetMapping("/search")
    public List<VisitorDto> searchVisitors(@RequestParam String name) {
        return visitorFacade.searchVisitorsByName(name);
    }

}
