package com.AWS.Figma.Dashboard.repository;

import com.AWS.Figma.Dashboard.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findBySaleDate(LocalDate saleDate);
}
