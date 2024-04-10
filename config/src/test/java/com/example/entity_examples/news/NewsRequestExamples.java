package com.example.entity_examples.news;

import com.example.dto.news.request.CreateNewsRequest;
import com.example.dto.news.request.UpdateNewsRequest;

public class NewsRequestExamples {

    public final static CreateNewsRequest VALID_CREATE_SPORT_NEWS_REQUEST = CreateNewsRequest.builder()
            .userId(1L)
            .categoryId(1L)
            .title("Football")
            .content("Some info about football")
            .build();

    public final static CreateNewsRequest INVALID_CREATE_SPORT_NEWS_REQUEST = CreateNewsRequest.builder()
            .userId(1L)
            .categoryId(1L)
            .title("    ")
            .content("Some info about football")
            .build();

    public final static UpdateNewsRequest VALID_UPDATE_SPORT_NEWS_REQUEST = UpdateNewsRequest.builder()
            .userId(1L)
            .content("Some new info about football")
            .build();
}
