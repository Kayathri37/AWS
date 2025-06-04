package com.AWS.Figma.Repo;



import com.AWS.Figma.Entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepo extends JpaRepository<Register,Long> {
    Register findByEmailIdAndPassword(String emailId, String password);
}
