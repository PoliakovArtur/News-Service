package com.example.facade;

import com.example.dto.comment.request.CreateCommentRequest;
import com.example.dto.comment.request.UpdateCommentRequest;
import com.example.dto.comment.response.CommentResponse;
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

    public List<CommentResponse> findByNews(Long newsId) {
        return mapper.toDtoList(service.findByNews(newsId));
    }

    public CommentResponse findById(Long id) {
        return mapper.toDto(service.findById(id));
    }

    public CommentResponse save(CreateCommentRequest request) {
        return mapper.toDto(service.save(request.getUserId(), request.getNewsId(), mapper.fromDto(request)));
    }

    public CommentResponse updateById(Long commentId, UpdateCommentRequest request) {
        return mapper.toDto(service.updateById(commentId, mapper.fromDto(request)));
    }

    public void deleteById(Long userId, Long commentId) {
        service.deleteById(commentId);
    }
}
