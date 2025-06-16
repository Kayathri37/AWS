package com.AWS.Figma.category.entity;



import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "category_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HierarchyItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HierarchyType type; // CATEGORY, BRAND, TYPE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private HierarchyItem parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HierarchyItem> children;

    @Column(nullable = false)
    private boolean active = true;

    @Column(name = "category_level")
    private Integer level; // 1=Category, 2=Brand, 3=Type
}
