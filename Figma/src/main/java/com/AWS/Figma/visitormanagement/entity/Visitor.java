package com.AWS.Figma.visitormanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "visitors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long srNo;

    @Column(unique = true, updatable = false)
    private String rfiId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String organisation;
}
