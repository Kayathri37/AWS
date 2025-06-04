package com.AWS.Figma.Controller;




import com.AWS.Figma.DTO.ApiResponse;
import com.AWS.Figma.DTO.SingUpDto;
import com.AWS.Figma.Service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    @Autowired
    ApiService apiService;


    @PostMapping("/singup")
    public ResponseEntity<ApiResponse> create(@RequestBody SingUpDto signupdto) {

        ApiResponse apiResponse = apiService.create(signupdto);

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}