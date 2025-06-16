// Enum for Hierarchy Types
package com.AWS.Figma.category.entity;

public enum HierarchyType {
    CATEGORY(1),
    BRAND(2),
    TYPE(3);

    private final int level;

    HierarchyType(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}