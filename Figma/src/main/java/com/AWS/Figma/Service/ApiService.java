package com.AWS.Figma.Service;




import com.AWS.Figma.Commen.BadRequest;
import com.AWS.Figma.Commen.error;
import com.AWS.Figma.DTO.ApiResponse;
import com.AWS.Figma.DTO.LoginRequest;
import com.AWS.Figma.DTO.LoginResponse;
import com.AWS.Figma.DTO.SingUpDto;
import com.AWS.Figma.Entity.Register;
import com.AWS.Figma.Facade.RegisterValidation;
import com.AWS.Figma.Repo.RegisterRepo;
import com.AWS.Figma.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtToken jwtTokenUtil;


    public ApiResponse singup(SingUpDto dto) {

        List<error> errs = registerValidation.validateTheRegisterData(dto);
        if (!errs.isEmpty()) {
            throw new BadRequest("BadRequest", errs);
        }

        Register reg = new Register();
        reg.setName(dto.getName());
        reg.setEmailId(dto.getEmailId());

        String hashed = passwordEncoder.encode(dto.getPassword());
        reg.setPassword(hashed);

        reg.setAge(dto.getAge());
        reg.setLocation(dto.getLocation());

        registerRepo.save(reg);

        ApiResponse res = new ApiResponse();
        Map<String,Object> data = new HashMap<>();
        data.put("UserDetails", reg);
        res.setData(data);
        return res;
    }
    public LoginResponse login(LoginRequest loginRequest) {

        Register user = registerRepo.findByEmailId(loginRequest.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String token = jwtTokenUtil.createToken(user);

        return new LoginResponse(token, user.getEmailId());
    }



}
