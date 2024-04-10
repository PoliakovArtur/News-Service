package com.example.entity_examples.category;

public class CategoryJsonExamples {

    public final static String VALID_UPDATE_SPORT_CATEGORY_JSON =
            """
                    {
                        "userId": 1,
                        "description": "News about sport and athletes"
                    }
                    """;

    public final static String INVALID_UPDATE_SPORT_CATEGORY_JSON =
            """
                    {
                        "userId": 1,
                        "title": ""
                    }
            """;
}
