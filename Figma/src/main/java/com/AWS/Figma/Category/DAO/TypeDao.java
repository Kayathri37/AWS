package com.AWS.Figma.Category.DAO;

import aj.org.objectweb.asm.Type;
import com.AWS.Figma.Category.Query.TypeQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypeDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Type> findByBrandId(Long brandId) {
        return entityManager.createNativeQuery(TypeQueries.FIND_TYPE_BY_BRAND_ID, Type.class)
                .setParameter("brand_id", brandId)
                .getResultList();
    }

    public Type save(Type type) {
        entityManager.persist(type);
        return type;
    }
}
