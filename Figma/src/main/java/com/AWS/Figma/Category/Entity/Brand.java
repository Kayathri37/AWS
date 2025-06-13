package com.AWS.Figma.Category.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity

public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // If you have a relation to Category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Getters and Setters
}
