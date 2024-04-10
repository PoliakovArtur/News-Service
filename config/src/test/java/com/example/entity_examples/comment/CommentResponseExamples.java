package com.example.entity_examples.comment;

import com.example.dto.comment.response.CommentResponse;

import java.time.LocalDateTime;
import java.util.List;

public class CommentResponseExamples {

    public final static CommentResponse COMMENT_RESPONSE = CommentResponse.builder()
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

    public final static List<CommentResponse> COMMENT_RESPONSE_LIST = List.of(COMMENT_RESPONSE);

}
