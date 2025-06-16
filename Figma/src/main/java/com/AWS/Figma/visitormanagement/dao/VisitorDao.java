package com.AWS.Figma.visitormanagement.dao;

import com.AWS.Figma.visitormanagement.entity.Visitor;
import com.AWS.Figma.visitormanagement.query.VisitorQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VisitorDao {

    @Autowired
    private VisitorQuery visitorQuery;

    public Visitor save(Visitor visitor) {
        return visitorQuery.save(visitor);
    }

    public List<Visitor> findAll() {
        return visitorQuery.findAll();
    }

    public List<Visitor> findByName(String name) {
        return visitorQuery.findByNameContainingIgnoreCase(name);
    }

    public long count() {
        return visitorQuery.count();
    }
}
