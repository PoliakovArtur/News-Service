package com.example.mapper;

import com.example.dto.comment.request.CreateCommentRequest;
import com.example.dto.comment.request.UpdateCommentRequest;
import com.example.dto.comment.response.CommentResponse;
import com.example.model.Comment;
import com.example.utils.annotations.CommentMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    Comment fromDto(CreateCommentRequest request);

    Comment fromDto(UpdateCommentRequest request);

    @Mapping(target = "author", source = "user.name")
    CommentResponse toDto(Comment comment);

    @CommentMapping
    @Mapping(target = "author", source = "user.name")
    List<CommentResponse> toDtoList(Iterable<Comment> comments);
}
