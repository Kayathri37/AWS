package com.AWS.Figma.Category.Query;

public class BrandQuery {
    public static final String FIND_BRAND_BY_ID = """
        SELECT * FROM Brand WHERE id = :id
    """;
}
