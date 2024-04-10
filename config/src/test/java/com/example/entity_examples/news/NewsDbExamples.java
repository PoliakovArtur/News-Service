package com.example.entity_examples.news;

import com.example.filter.impl.NewsFilter;
import com.example.model.News;
import org.springframework.data.util.Pair;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.entity_examples.category.CategoryDbExamples.MUSIC_CATEGORY;
import static com.example.entity_examples.category.CategoryDbExamples.SPORT_CATEGORY;
import static com.example.entity_examples.user.UserDbExamples.JANE_DOE;
import static com.example.entity_examples.user.UserDbExamples.JOHN_DOE;

public class NewsDbExamples {

    public final static News SPORT_NEWS = News.builder()
            .id(1L)
            .category(SPORT_CATEGORY)
            .user(JOHN_DOE)
            .title("Football")
            .content("Some info about football")
            .createAt(LocalDateTime.of(2022, 4, 15, 0, 0, 0))
            .updateAt(LocalDateTime.of(2022, 4, 15, 0, 0, 0))
            .build();

    public final static News UPDATED_SPORT_NEWS = News.builder()
            .id(1L)
            .category(SPORT_CATEGORY)
            .user(JOHN_DOE)
            .title("Football")
            .content("Some new info about football")
            .createAt(LocalDateTime.of(2022, 4, 15, 0, 0, 0))
            .updateAt(LocalDateTime.of(2022, 4, 17, 0, 0, 0))
            .build();

    public final static News SPORT_NEWS_AFTER_MAPPING = News.builder()
            .title("Football")
            .content("Some info about football")
            .build();

    public final static News UPDATE_SPORT_NEWS_AFTER_MAPPING = News.builder()
            .content("Some new info about football")
            .build();

    public final static List<Pair<News, Integer>> NEWS_WITH_COMMENTS_COUNT = List.of(Pair.of(SPORT_NEWS, 3));

    public final static News MUSIC_NEWS = News.builder()
            .id(2L)
            .category(MUSIC_CATEGORY)
            .user(JANE_DOE)
            .title("Folk")
            .content("Some info about folk")
            .createAt(LocalDateTime.of(2022, 5, 4, 0, 0, 0))
            .updateAt(LocalDateTime.of(2022, 4, 15, 0, 0, 0))
            .build();

    public final static NewsFilter SIMPLE_NEWS_FILTER = NewsFilter.builder()
            .pageNumber(0)
            .pageSize(1)
            .desc(false)
            .build();
}
