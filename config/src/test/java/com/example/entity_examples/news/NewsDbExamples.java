package com.example.entity_examples.news;

import com.example.filter.impl.NewsFilter;
import com.example.model.News;
import org.springframework.data.util.Pair;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.entity_examples.category.CategoryDbExamples.CATEGORY;
import static com.example.entity_examples.user.UserDbExamples.USER;

public class NewsDbExamples {

    public final static News NEWS = News.builder()
            .id(1L)
            .category(CATEGORY)
            .user(USER)
            .title("Football")
            .content("Some info about football")
            .createAt(LocalDateTime.of(2022, 4, 15, 0, 0, 0))
            .updateAt(LocalDateTime.of(2022, 4, 15, 0, 0, 0))
            .build();

    public final static News UPDATED_NEWS = News.builder()
            .id(1L)
            .category(CATEGORY)
            .user(USER)
            .title("Football")
            .content("Some new info about football")
            .createAt(LocalDateTime.of(2022, 4, 15, 0, 0, 0))
            .updateAt(LocalDateTime.of(2022, 4, 17, 0, 0, 0))
            .build();

    public final static News NEWS_AFTER_MAPPING = News.builder()
            .title("Football")
            .content("Some info about football")
            .build();

    public final static News UPDATE_NEWS_AFTER_MAPPING = News.builder()
            .content("Some new info about football")
            .build();

    public final static List<Pair<News, Integer>> NEWS_WITH_COMMENTS_COUNT = List.of(Pair.of(NEWS, 3));

    public final static NewsFilter SIMPLE_NEWS_FILTER = NewsFilter.builder()
            .pageNumber(0)
            .pageSize(1)
            .desc(false)
            .build();
}
