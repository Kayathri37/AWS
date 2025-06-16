package com.AWS.Figma.controller;

import com.AWS.Figma.dto.LoginRequestDto;
import com.AWS.Figma.dto.LoginResponseDto;
import com.AWS.Figma.dto.ResponseDto;
import com.AWS.Figma.dto.UserReqDto;
import com.AWS.Figma.entity.User;
import com.AWS.Figma.service.UserService;

import com.AWS.Figma.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtToken jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody UserReqDto userReqDto) {
        return ResponseEntity.ok(userService. signup(userReqDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail());

        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        if (!user.isActive()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is inactive");
        }

        String token = jwtUtil.createToken(user);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
