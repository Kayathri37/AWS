package com.AWS.Figma.DAO;

import com.AWS.Figma.Entity.Register;

import java.util.Optional;

public interface RegisterCustomDao {
    Optional<Register> findByEmailAndPassword(String emailId, String password);
}
