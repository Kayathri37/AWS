package com.AWS.Figma.category.dto;


import com.AWS.Figma.category.entity.HierarchyItem;
import com.AWS.Figma.category.entity.HierarchyType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HierarchyRequestDto {
    private String name;
    private HierarchyType type;
    private Long categoryId;

    public HierarchyItem toEntity() {
        HierarchyItem item = new HierarchyItem();
        item.setName(this.name);
        item.setType(this.type);
        item.setLevel(this.type.getLevel());
        return item;
    }
}