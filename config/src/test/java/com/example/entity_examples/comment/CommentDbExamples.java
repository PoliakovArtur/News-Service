package com.example.entity_examples.comment;

import com.example.model.Comment;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.entity_examples.news.NewsDbExamples.MUSIC_NEWS;
import static com.example.entity_examples.news.NewsDbExamples.SPORT_NEWS;
import static com.example.entity_examples.user.UserDbExamples.JANE_DOE;
import static com.example.entity_examples.user.UserDbExamples.JOHN_DOE;


public class CommentDbExamples {

    public final static Comment SPORT_NEWS_COMMENT = Comment.builder()
            .id(1L)
            .news(SPORT_NEWS)
            .user(JOHN_DOE)
            .content("It's Good")
            .createAt(LocalDateTime.of(2022, 4, 20, 20, 10, 10))
            .build();

    public final static Comment UPDATED_SPORT_NEWS_COMMENT = Comment.builder()
            .id(1L)
            .news(SPORT_NEWS)
            .user(JOHN_DOE)
            .content("It's very Good")
            .createAt(LocalDateTime.of(2022, 4, 20, 20, 10, 10))
            .build();

    public final static Comment UPDATE_REQUEST_COMMENT_AFTER_MAPPING = Comment.builder()
            .content("It's very Good")
            .build();

    public final static Comment CREATE_REQUEST_COMMENT_AFTER_MAPPING = Comment.builder()
            .content("It's Good")
            .build();

    public final static List<Comment> SPORT_NEWS_COMMENT_LIST = List.of(
            SPORT_NEWS_COMMENT,
            Comment.builder()
                    .id(2L)
                    .news(SPORT_NEWS)
                    .user(JANE_DOE)
                    .content("I dont think")
                    .createAt(LocalDateTime.of(2022, 4, 20, 20, 15, 20))
                    .build(),
            Comment.builder()
                    .id(3L)
                    .news(SPORT_NEWS)
                    .user(JOHN_DOE)
                    .content("you're mistake")
                    .createAt(LocalDateTime.of(2022, 4, 20, 20, 20, 0))
                    .build());

    public final static List<Comment> MUSIC_NEWS_COMMENT_LIST = List.of(
            Comment.builder()
                    .id(4L)
                    .news(MUSIC_NEWS)
                    .user(JOHN_DOE)
                    .content("like folk")
                    .createAt(LocalDateTime.of(2022, 4, 20, 19, 10, 0))
                    .build(),
            Comment.builder()
                    .id(5L)
                    .news(SPORT_NEWS)
                    .user(JANE_DOE)
                    .content("dont like folk")
                    .createAt(LocalDateTime.of(2022, 4, 20, 19, 20, 0))
                    .build());
}
