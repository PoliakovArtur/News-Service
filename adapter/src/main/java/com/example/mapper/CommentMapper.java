package com.example.mapper;

import com.example.dto.comment.request.CreateCommentDto;
import com.example.dto.comment.request.UpdateCommentDto;
import com.example.dto.comment.response.CommentInfoDto;
import com.example.model.Comment;
import com.example.utils.annotations.CommentMapping;
import org.hibernate.sql.Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    Comment fromDto(CreateCommentDto request);

    Comment fromDto(UpdateCommentDto request);

    @Mapping(target = "author", source = "user.name")
    CommentInfoDto toDto(Comment comment);

    @CommentMapping
    @Mapping(target = "author", source = "user.name")
    List<CommentInfoDto> toDtoList(List<Comment> comments);
}
