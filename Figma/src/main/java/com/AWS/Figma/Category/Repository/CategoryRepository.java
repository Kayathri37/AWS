package com.AWS.Figma.Category.Repository;

import com.AWS.Figma.Category.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {}
