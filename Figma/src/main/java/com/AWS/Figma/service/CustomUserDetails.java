package com.AWS.Figma.service;


import com.AWS.Figma.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
    public CustomUserDetails(User user) {
    }
}
