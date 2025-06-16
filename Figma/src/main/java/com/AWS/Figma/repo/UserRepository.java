package com.AWS.Figma.repo;

import com.AWS.Figma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByMobileNumber(String mobileNumber);

    Optional<User> findByEmail(String email);
}
