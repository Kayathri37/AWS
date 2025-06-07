package com.AWS.Figma.Entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "AWS_Users")
@NoArgsConstructor
@Data
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String emailId;
    private String location;
    private int age;
    private boolean isActive = true;
    private String password;


    public Register(long id,String name, String emailId, String location, int age, boolean isActive, String password) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.location = location;
        this.age = age;
        this.isActive = isActive;
        this.password = password;
    }
}
