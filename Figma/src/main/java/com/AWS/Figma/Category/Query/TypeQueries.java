package com.AWS.Figma.Category.Query;

public class TypeQueries {
    public static final String FIND_TYPE_BY_BRAND_ID = """
        SELECT * FROM type WHERE brand_id = :brand_id
    """;
}
