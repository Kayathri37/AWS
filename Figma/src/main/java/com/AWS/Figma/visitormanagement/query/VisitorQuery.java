package com.AWS.Figma.visitormanagement.query;

import com.AWS.Figma.visitormanagement.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitorQuery extends JpaRepository<Visitor, Long> {
    List<Visitor> findByNameContainingIgnoreCase(String name);
}
