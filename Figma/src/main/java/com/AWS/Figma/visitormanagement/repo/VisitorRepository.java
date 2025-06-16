package com.AWS.Figma.visitormanagement.repo;

import com.AWS.Figma.visitormanagement.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    List<Visitor> findByNameContainingIgnoreCase(String name);
}



