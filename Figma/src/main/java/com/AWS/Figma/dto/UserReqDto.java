package com.AWS.Figma.dto;


import com.AWS.Figma.entity.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReqDto {
    private String name;
    private String email;
    private String password;
    private String mobileNumber;

    public User toEntity() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setMobileNumber(this.mobileNumber);
        return user;
    }
}

