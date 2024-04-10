package com.example.entity_examples.news;

public class NewsJsonExamples {

    public final static String VALID_UPDATE_NEWS_JSON =
            """
                    {
                        "userId": 1,
                        "content": "Some new info about football"
                    }
            """;

    public final static String INVALID_UPDATE_NEWS_JSON =
            """
                    {
                        "userId": 1,
                        "content": "   "
                    }
            """;

}
