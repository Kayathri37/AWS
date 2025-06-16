package com.AWS.Figma.Dashboard.repository;

import com.AWS.Figma.Dashboard.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    List<Sales> findBySaleDateBetween(LocalDate startDate, LocalDate endDate);
}
