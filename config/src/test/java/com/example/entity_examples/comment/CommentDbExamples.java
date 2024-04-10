package com.example.entity_examples.comment;

import com.example.model.Comment;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.entity_examples.news.NewsDbExamples.NEWS;
import static com.example.entity_examples.user.UserDbExamples.USER;


public class CommentDbExamples {

    public final static Comment COMMENT = Comment.builder()
            .id(1L)
            .news(NEWS)
            .user(USER)
            .content("It's Good")
            .createAt(LocalDateTime.of(2022, 4, 20, 20, 10, 10))
            .build();

    public final static Comment UPDATED_COMMENT = Comment.builder()
            .id(1L)
            .news(NEWS)
            .user(USER)
            .content("It's very Good")
            .createAt(LocalDateTime.of(2022, 4, 20, 20, 10, 10))
            .build();

    public final static Comment UPDATE_REQUEST_COMMENT_AFTER_MAPPING = Comment.builder()
            .content("It's very Good")
            .build();

    public final static Comment CREATE_REQUEST_COMMENT_AFTER_MAPPING = Comment.builder()
            .content("It's Good")
            .build();

    public final static List<Comment> COMMENT_LIST = List.of(COMMENT);
}
