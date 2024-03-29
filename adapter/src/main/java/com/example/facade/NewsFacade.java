package com.example.facade;

import com.example.dto.news.request.UpdateNewsDto;
import com.example.dto.news.response.FullNewsInfoDto;
import com.example.dto.news.request.CreateNewsDto;
import com.example.dto.news.response.ShortNewsInfoDto;
import com.example.dto.news.response.UpdateNewsInfoDto;
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

    public List<ShortNewsInfoDto> findAll(NewsFilter newsFilter) {
        return mapper.toShortNewsResponseList(service.findAll(newsFilter));
    }

    public FullNewsInfoDto findById(Long id) {
        return mapper.toFullNewsResponse(service.findById(id));
    }

    public UpdateNewsInfoDto save(CreateNewsDto request) {
        return mapper.toUpdateNewsResponse(service.save(request.getUserId(), request.getCategoryId(), mapper.fromDto(request)));
    }

    public UpdateNewsInfoDto updateById(Long newsId, UpdateNewsDto request) {
        return mapper.toUpdateNewsResponse(service.updateById(newsId, mapper.fromDto(request)));
    }

    public void deleteById(Long userId, Long commentId) {
        service.deleteById(commentId);
    }
}
