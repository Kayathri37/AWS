package com.AWS.Figma.Service;




import com.AWS.Figma.Commen.BadRequest;
import com.AWS.Figma.Commen.error;
import com.AWS.Figma.DTO.ApiResponse;
import com.AWS.Figma.DTO.LoginRequest;
import com.AWS.Figma.DTO.SingUpDto;
import com.AWS.Figma.Entity.Register;
import com.AWS.Figma.Facade.RegisterValidation;
import com.AWS.Figma.Repo.RegisterRepo;
import com.AWS.Figma.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiService {

    @Autowired
    RegisterValidation registerValidation;

    @Autowired
    JwtToken jwtToken;

    @Autowired
    RegisterRepo registerRepo;

    public ApiResponse create(SingUpDto singUpDto) {

        ApiResponse apiResponse = new ApiResponse();
        List<error> check = registerValidation.validateTheRegisterData(singUpDto);

        if (!check.isEmpty()) {
            throw new BadRequest("BadRequest", check);
        }
        Register register = new Register();
        register.setName(singUpDto.getName());
        register.setEmailId(singUpDto.getEmailId());
        register.setPassword(singUpDto.getPassword());
        register.setAge(singUpDto.getAge());
        register.setLocation(singUpDto.getLocation());
        registerRepo.save(register);
        Map<String, Object> data = new HashMap<>();
        data.put("UserDetails", register);
        apiResponse.setData(data);
        return apiResponse;
    }

    public ApiResponse login(LoginRequest registerDto) {
        ApiResponse apiResponse = new ApiResponse();
        Register register = registerRepo.findByEmailIdAndPassword(registerDto.getEmail(), registerDto.getPassword());

        if (register == null) {
            apiResponse.setError("User not found.....");
        }
        String token = jwtToken.createToken(register);

        Map<String, Object> jwt = new HashMap<>();
        jwt.put("Token", token);

        apiResponse.setData(jwt);
        return apiResponse;
    }

}
