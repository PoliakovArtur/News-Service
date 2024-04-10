package com.example.entity_examples.user;

public class UserJsonExamples {

    public final static String VALID_UPDATE_USER_JSON =
            """
            {
                "aboutMe": "Like cats"
            }
            """;

    public final static String INVALID_UPDATE_USER_JSON =
            """
            {
                "age": -1
            }
            """;

}
