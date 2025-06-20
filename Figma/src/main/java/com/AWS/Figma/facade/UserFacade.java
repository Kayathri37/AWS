package com.AWS.Figma.facade;




import com.AWS.Figma.dto.ResponseDto;
import com.AWS.Figma.dto.UserReqDto;
import com.AWS.Figma.entity.User;

import com.AWS.Figma.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    @Autowired
    private UserRepository userRepository;

    public ResponseDto signup(UserReqDto userReqDto) {
        User user = userReqDto.toEntity();


        if (userRepository.existsByEmail(user.getEmail())) {
            return new ResponseDto("Error: Email already exists", false);
        }

        if (userRepository.existsByMobileNumber(user.getMobileNumber())) {
            return new ResponseDto("Error: Mobile number already registered", false);
        }


        userRepository.save(user);
        return new ResponseDto("User registered successfully", true);
    }
}

