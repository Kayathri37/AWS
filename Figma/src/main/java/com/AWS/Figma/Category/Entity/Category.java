package com.AWS.Figma.Category.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Category {

    // Getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or AUTO, SEQUENCE based on DB
    private Long id;

    private String name;

    // Other fields and methods...

}
