package com.AWS.Figma.service;

import com.AWS.Figma.dto.ResponseDto;
import com.AWS.Figma.dto.UserReqDto;
import com.AWS.Figma.entity.User;
import com.AWS.Figma.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseDto signup(UserReqDto userReqDto) {
        User user = userReqDto.toEntity();


        if (userRepository.existsByEmail(user.getEmail())) {
            return new ResponseDto("Error: Email already exists", false);
        }

        if (userRepository.existsByMobileNumber(user.getMobileNumber())) {
            return new ResponseDto("Error: Mobile number already registered", false);
        }


        user.setPassword(passwordEncoder.encode(user.getPassword()));


        userRepository.save(user);
        return new ResponseDto("User registered successfully", true);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>()
        );
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
