package com.AWS.Figma.Category.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "type")
public class Type {

    // --- Getters and Setters ---
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

}
