package com.AWS.Figma.InventoryManagement.query;

public class InventoryQuery {

    public static final String FIND_BY_ID = """
        SELECT * FROM inventory_items WHERE id = :id
    """;

    public static final String FIND_BY_DESCRIPTION_PREFIX = """
        SELECT * FROM inventory_items WHERE LOWER(description) LIKE CONCAT(LOWER(:prefix), '%')
    """;
}
