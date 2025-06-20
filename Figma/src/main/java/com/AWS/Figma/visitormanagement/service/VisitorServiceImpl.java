package com.AWS.Figma.visitormanagement.service;

import com.AWS.Figma.visitormanagement.dao.VisitorDao;
import com.AWS.Figma.visitormanagement.dto.VisitorDto;
import com.AWS.Figma.visitormanagement.entity.Visitor;
import com.AWS.Figma.visitormanagement.util.VisitorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorDao visitorDao;
    @Override
    public VisitorDto addVisitor(VisitorDto dto) {
        Visitor entity = VisitorMapper.toEntity(dto);

        long nextId = visitorDao.count() + 1;
        String rfiId = String.format("%014d", nextId);
        entity.setRfiId(rfiId);

        Visitor saved = visitorDao.save(entity);
        return VisitorMapper.toDto(saved);
    }


    @Override
    public List<VisitorDto> getAllVisitors() {
        return visitorDao.findAll()
                .stream()
                .map(VisitorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VisitorDto> searchVisitorsByName(String name) {
        return visitorDao.findByName(name)
                .stream()
                .map(VisitorMapper::toDto)
                .collect(Collectors.toList());
    }
}
