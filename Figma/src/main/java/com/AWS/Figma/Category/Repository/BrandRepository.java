package com.AWS.Figma.Category.Repository;

import com.AWS.Figma.Category.Entity.Brand;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findById(Long id);
}

