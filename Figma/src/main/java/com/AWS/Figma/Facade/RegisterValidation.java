package com.AWS.Figma.Facade;

import com.AWS.Figma.Commen.error;
import com.AWS.Figma.DTO.SingUpDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegisterValidation {

    public List<error> validateTheRegisterData(SingUpDto singUpDto) {
        List<error> errors = new ArrayList<>();

        if (singUpDto.getName() == null || singUpDto.getName().trim().isEmpty()) {
            errors.add(new error("Name", "Name value is null or empty"));
        }

        if (singUpDto.getEmailId() == null || singUpDto.getEmailId().trim().isEmpty()) {
            errors.add(new error("Email", "Email value is null or empty"));
        }

        if (singUpDto.getPassword() == null || singUpDto.getPassword().trim().isEmpty()) {
            errors.add(new error("Password", "Password value is null or empty"));
        } else {
            String password = singUpDto.getPassword();
            if (password.length() < 8) {
                errors.add(new error("Password", "Password must be at least 8 characters long"));
            }
            if (!password.matches(".*[A-Z].*")) {
                errors.add(new error("Password", "Password must contain at least one uppercase letter"));
            }
            if (!password.matches(".*[a-z].*")) {
                errors.add(new error("Password", "Password must contain at least one lowercase letter"));
            }
            if (!password.matches(".*\\d.*")) {
                errors.add(new error("Password", "Password must contain at least one digit"));
            }
            if (!password.matches(".*[@#$%^&+=!].*")) {
                errors.add(new error("Password", "Password must contain at least one special character (@#$%^&+=!)"));
            }
        }

        if (singUpDto.getAge() == null || singUpDto.getAge() <= 0) {
            errors.add(new error("Age", "Age value is null or invalid"));
        }

        if (singUpDto.getLocation() == null || singUpDto.getLocation().trim().isEmpty()) {
            errors.add(new error("Location", "Location value is null or empty"));
        }

        return errors;
    }
}
