package com.example.facade;

import com.example.dto.news.request.UpdateNewsRequest;
import com.example.dto.news.response.FullNewsResponse;
import com.example.dto.news.request.CreateNewsRequest;
import com.example.dto.news.response.ShortNewsResponse;
import com.example.dto.news.response.UpdateNewsResponse;
import com.example.filter.impl.NewsFilter;
import com.example.mapper.NewsMapper;
import com.example.service.NewsService;
import com.example.utils.annotations.Facade;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Facade
@RequiredArgsConstructor
public class NewsFacade {

    private final NewsService service;
    private final NewsMapper mapper;

    public List<ShortNewsResponse> findAll(NewsFilter newsFilter) {
        return mapper.toShortNewsResponseList(service.findAll(newsFilter));
    }

    public FullNewsResponse findById(Long id) {
        return mapper.toFullNewsResponse(service.findById(id));
    }

    public UpdateNewsResponse save(CreateNewsRequest request) {
        return mapper.toUpdateNewsResponse(service.save(request.getUserId(), request.getCategoryId(), mapper.fromDto(request)));
    }

    public UpdateNewsResponse updateById(Long newsId, UpdateNewsRequest request) {
        return mapper.toUpdateNewsResponse(service.updateById(newsId, mapper.fromDto(request)));
    }

    public void deleteById(Long userId, Long commentId) {
        service.deleteById(commentId);
    }
}
