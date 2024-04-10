package com.example.entity_examples.comment;

import com.example.dto.comment.request.CreateCommentRequest;
import com.example.dto.comment.request.UpdateCommentRequest;

public class CommentRequestExamples {

    public final static CreateCommentRequest VALID_CREATE_COMMENT_REQUEST = CreateCommentRequest.builder()
            .newsId(1L).userId(1L).content("It's Good").build();

    public final static CreateCommentRequest INVALID_CREATE_COMMENT_REQUEST = CreateCommentRequest.builder()
            .newsId(1L).userId(1L).content(" ").build();

    public final static UpdateCommentRequest VALID_UPDATE_COMMENT_REQUEST = UpdateCommentRequest.builder()
            .userId(1L)
            .newsId(1L)
            .content("It's very Good")
            .build();

    public final static UpdateCommentRequest INVALID_UPDATE_COMMENT_REQUEST = UpdateCommentRequest.builder()
            .userId(1L)
            .newsId(1L)
            .content("     ")
            .build();
}
