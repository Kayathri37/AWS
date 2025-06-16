package com.AWS.Figma.dao;
import com.AWS.Figma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}

