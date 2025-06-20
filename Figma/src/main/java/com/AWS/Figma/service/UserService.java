package com.AWS.Figma.service;

import com.AWS.Figma.dto.ResponseDto;
import com.AWS.Figma.dto.UserReqDto;
import com.AWS.Figma.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    ResponseDto  signup (UserReqDto userReqDto);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    User findByEmail(String email);
}
