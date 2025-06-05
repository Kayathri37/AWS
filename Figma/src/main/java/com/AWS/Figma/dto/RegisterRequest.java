package com.AWS.Figma.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobileNumber;
}
