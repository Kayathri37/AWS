package com.AWS.Figma.Query;

public class RegisterQuery {

    public static final String FIND_BY_EMAIL_AND_PASSWORD = """
        SELECT * FROM Register WHERE email_id = :email_id AND password = :password
    """;
}
