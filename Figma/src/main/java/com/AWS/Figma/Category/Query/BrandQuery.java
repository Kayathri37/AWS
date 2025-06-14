package com.AWS.Figma.Category.Query;

public class BrandQuery {
    public static final String FIND_BRAND_BY_ID = """
        SELECT * FROM Brand WHERE id = :id
    """;
    public static final String UPDATE_BRAND_BY_ID =

            "UPDATE brand SET name = :name WHERE id = :id";
}

