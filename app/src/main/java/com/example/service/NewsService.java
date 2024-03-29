package com.example.service;

import com.example.filter.impl.NewsFilter;
import com.example.model.News;

import java.util.Map;

public interface NewsService {

    Map<News, Integer> findAll(NewsFilter newsFilter);

    News findById(Long id);

    News save(Long userId, Long categoryId, News news);

    News updateById(Long newsId, News fromDto);

    void deleteById(Long newsId);

    void checkExistById(Long id);
}
