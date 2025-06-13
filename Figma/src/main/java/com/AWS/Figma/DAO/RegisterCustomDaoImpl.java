package com.AWS.Figma.DAO;

import com.AWS.Figma.Entity.Register;
import com.AWS.Figma.Query.RegisterQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RegisterCustomDaoImpl implements RegisterCustomDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Register> findByEmailAndPassword(String emailId, String password) {
        try {
            Register register = (Register) entityManager.createNativeQuery(RegisterQuery.FIND_BY_EMAIL_AND_PASSWORD, Register.class)
                    .setParameter("email_id", emailId)
                    .setParameter("password", password)
                    .getSingleResult();
            return Optional.of(register);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
