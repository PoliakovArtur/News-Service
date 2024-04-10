package com.example.entity_examples.news;

import com.example.dto.news.response.FullNewsResponse;
import com.example.dto.news.response.ShortNewsResponse;
import com.example.dto.news.response.UpdateNewsResponse;

import java.time.LocalDateTime;
import java.util.List;

public class NewsResponseExamples {

    public final static UpdateNewsResponse CREATE_NEWS_RESPONSE = UpdateNewsResponse.builder()
            .id(1L)
            .title("Football")
            .content("Some info about football")
            .category("Sport")
            .author("John Doe")
            .build();


    public final static UpdateNewsResponse UPDATE_NEWS_RESPONSE = UpdateNewsResponse.builder()
            .id(1L)
            .title("Football")
            .content("Some new info about football")
            .category("Sport")
            .author("John Doe")
            .build();

    public final static ShortNewsResponse SHORT_NEWS_RESPONSE = ShortNewsResponse.builder()
            .id(1L)
            .title("Football")
            .category("Sport")
            .author("John Doe")
            .createAt(LocalDateTime.of(2022, 4, 15, 0, 0, 0))
            .commentsCount(3)
            .build();

    public final static List<ShortNewsResponse> SHORT_NEWS_RESPONSE_LIST = List.of(SHORT_NEWS_RESPONSE);

    public final static FullNewsResponse FULL_NEWS_RESPONSE = FullNewsResponse.builder()
            .id(1L)
            .title("Football")
            .category("Sport")
            .author("John Doe")
            .content("Some info about football")
            .createAt(LocalDateTime.of(2022, 4, 15, 0, 0, 0))
            .updateAt(LocalDateTime.of(2022, 4, 15, 0, 0, 0))
            .build();

}
