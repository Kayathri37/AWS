
package com.AWS.Figma.category.dto;


import com.AWS.Figma.category.entity.HierarchyItem;
import com.AWS.Figma.category.entity.HierarchyType;
import lombok.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HierarchyResponseDto {
    private Long id;
    private String name;
    private HierarchyType type;
    private Integer level;
    private Long categoryId;
    private String categoryName;
    private boolean active;
    private List<HierarchyResponseDto> children;

    public static HierarchyResponseDto fromEntity(HierarchyItem item) {
        HierarchyResponseDto dto = new HierarchyResponseDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setType(item.getType());
        dto.setLevel(item.getLevel());
        dto.setActive(item.isActive());

        if (item.getParent() != null) {
            dto.setCategoryId(item.getParent().getId());
            dto.setCategoryName(item.getParent().getName());
        }

        if (item.getChildren() != null && !item.getChildren().isEmpty()) {
            dto.setChildren(item.getChildren().stream()
                    .filter(HierarchyItem::isActive)
                    .map(HierarchyResponseDto::fromEntity)
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}