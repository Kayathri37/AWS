package com.AWS.Figma.Category.DAO;

import com.AWS.Figma.Category.Entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }
    @Transactional

    public Category editCategory(Category category) {
        return entityManager.merge(category);
    }
}
