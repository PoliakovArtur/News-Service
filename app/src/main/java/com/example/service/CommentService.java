package com.example.service;

import com.example.model.Comment;
import java.util.List;

public interface CommentService {

    Iterable<Comment> findByNews(Long newsId);

    Integer countByNews(Long newsId);

    Comment findById(Long id);

    Comment save(Long userId, Long newsId, Comment comment);

    Comment updateById(Long commentId, Comment fromDto);

    void deleteById(Long commentId);

}
