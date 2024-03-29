package com.example.service.impl;

import com.example.exception.NotFoundException;
import com.example.util.EntityUpdater;
import com.example.model.Comment;
import com.example.model.News;
import com.example.model.User;
import com.example.repository.CommentRepository;
import com.example.service.CommentService;
import com.example.service.NewsService;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.text.MessageFormat.format;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final UserService userService;
    private final EntityUpdater updater;
    private NewsService newsService;

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findByNews(Long newsId) {
        newsService.checkExistById(newsId);
        return repository.findByNewsId(newsId);
    }

    @Transactional(readOnly = true)
    @Override
    public Integer countByNews(Long newsId) {
        return repository.countByNewsId(newsId);
    }

    @Transactional(readOnly = true)
    @Override
    public Comment findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Комментарий с id {0} не найден", id)));
    }

    @Transactional
    @Override
    public Comment save(Long userId, Long newsId, Comment comment) {
        User user = userService.findById(userId);
        News news = newsService.findById(newsId);
        comment.setUser(user);
        comment.setNews(news);
        return repository.save(comment);
    }

    @Transactional
    @Override
    public Comment updateById(Long commentId, Comment fromDto) {
        Comment fromDB = findById(commentId);
        updater.updateComment(fromDB, fromDto);
        return repository.save(fromDB);
    }

    @Transactional
    @Override
    public void deleteById(Long commentId) {
        repository.deleteById(commentId);
    }

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
}
