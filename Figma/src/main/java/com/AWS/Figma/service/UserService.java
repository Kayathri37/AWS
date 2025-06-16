package com.AWS.Figma.service;

import com.AWS.Figma.dto.ResponseDto;
import com.AWS.Figma.dto.UserReqDto;
import com.AWS.Figma.entity.User;

public interface UserService {
    ResponseDto  signup (UserReqDto userReqDto);
    User findByEmail(String email);
}
