package com.AWS.Figma.Repo;



import com.AWS.Figma.Entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterRepo extends JpaRepository<Register,Long> {
   Register findByEmailIdAndPassword(String emailId, String password);
   Optional<Register> findByEmailId(String emailId);

}
