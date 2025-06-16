package com.AWS.Figma.category.query;
import com.AWS.Figma.category.entity.Category;
import org.springframework.data.jpa.domain.Specification;

public class CategoryQuery {

    public static Specification<Category> hasType(String type) {
        return (root, query, cb) -> cb.equal(root.get("type"), type);
    }


    public static Specification<Category> isActive() {
        return (root, query, cb) -> cb.isTrue(root.get("active"));
    }
}
