package com.AWS.Figma.Controller;




import com.AWS.Figma.DTO.ApiResponse;
import com.AWS.Figma.DTO.LoginRequest;
import com.AWS.Figma.DTO.LoginResponse;
import com.AWS.Figma.DTO.SingUpDto;
import com.AWS.Figma.Service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    @Autowired
    ApiService apiService;



    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody SingUpDto signupdto) {
        ApiResponse apiResponse = apiService.singup(signupdto);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = apiService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}