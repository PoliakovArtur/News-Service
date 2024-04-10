package com.example.service;

import com.example.filter.impl.NewsFilter;
import com.example.model.News;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Map;

public interface NewsService {

    List<Pair<News, Integer>> findAll(NewsFilter newsFilter);

    News findById(Long id);

    News save(Long userId, Long categoryId, News news);

    News updateById(Long newsId, News fromDto);

    void deleteById(Long newsId);

    void checkExistById(Long id);
}
