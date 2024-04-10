package com.example.entity_examples.comment;

import com.example.dto.comment.response.CommentResponse;

import java.time.LocalDateTime;
import java.util.List;

public class CommentResponseExamples {

    public final static CommentResponse SPORT_NEWS_COMMENT_RESPONSE = CommentResponse.builder()
            .id(1L)
            .author("John Doe")
            .content("It's Good")
            .createAt(LocalDateTime.of(2022, 4, 20, 20, 10, 10))
            .build();

    public final static CommentResponse UPDATED_COMMENT_RESPONSE = CommentResponse.builder()
            .id(1L)
            .author("John Doe")
            .content("It's very Good")
            .createAt(LocalDateTime.of(2022, 4, 20, 20, 10, 10))
            .build();

    public final static List<CommentResponse> SPORT_NEWS_COMMENTS_RESPONSE_LIST = List.of(
            SPORT_NEWS_COMMENT_RESPONSE,
            CommentResponse.builder()
                    .id(2L).author("Jane Doe")
                    .content("I dont think")
                    .createAt(LocalDateTime.of(2022, 4, 20, 20, 15, 20))
                    .build(),
            CommentResponse.builder()
                    .id(3L)
                    .author("John Doe")
                    .content("you're mistake")
                    .createAt(LocalDateTime.of(2022, 4, 20, 20, 20, 0))
                    .build());

    public final static List<CommentResponse> MUSIC_NEWS_COMMENTS_RESPONSE_LIST = List.of(
            CommentResponse.builder()
                    .id(4L)
                    .author("John Doe")
                    .content("like folk")
                    .createAt(LocalDateTime.of(2022, 4, 20, 19, 10, 0))
                    .build(),
            CommentResponse.builder()
                    .id(5L)
                    .author("Jane Doe")
                    .content("dont like folk")
                    .createAt(LocalDateTime.of(2022, 4, 20, 19, 20, 0))
                    .build());

}
