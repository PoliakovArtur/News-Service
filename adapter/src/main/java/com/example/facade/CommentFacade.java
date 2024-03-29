package com.example.facade;

import com.example.dto.comment.request.CreateCommentDto;
import com.example.dto.comment.request.UpdateCommentDto;
import com.example.dto.comment.response.CommentInfoDto;
import com.example.mapper.CommentMapper;
import com.example.service.CommentService;
import com.example.utils.annotations.Facade;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Facade
@RequiredArgsConstructor
public class CommentFacade {

    private final CommentService service;
    private final CommentMapper mapper;

    public List<CommentInfoDto> findByNews(Long newsId) {
        return mapper.toDtoList(service.findByNews(newsId));
    }

    public CommentInfoDto findById(Long id) {
        return mapper.toDto(service.findById(id));
    }

    public CommentInfoDto save(CreateCommentDto request) {
        return mapper.toDto(service.save(request.getUserId(), request.getNewsId(), mapper.fromDto(request)));
    }

    public CommentInfoDto updateById(Long commentId, UpdateCommentDto request) {
        return mapper.toDto(service.updateById(commentId, mapper.fromDto(request)));
    }

    public void deleteById(Long userId, Long commentId) {
        service.deleteById(commentId);
    }
}
