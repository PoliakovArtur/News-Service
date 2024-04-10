package com.example.facade;

import com.example.dto.news.response.FullNewsResponse;
import com.example.dto.news.response.ShortNewsResponse;
import com.example.dto.news.response.UpdateNewsResponse;
import com.example.service.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static com.example.entity_examples.news.NewsDbExamples.NEWS_WITH_COMMENTS_COUNT;
import static com.example.entity_examples.news.NewsDbExamples.SIMPLE_NEWS_FILTER;
import static com.example.entity_examples.news.NewsDbExamples.SPORT_NEWS;
import static com.example.entity_examples.news.NewsDbExamples.SPORT_NEWS_AFTER_MAPPING;
import static com.example.entity_examples.news.NewsDbExamples.UPDATED_SPORT_NEWS;
import static com.example.entity_examples.news.NewsDbExamples.UPDATE_SPORT_NEWS_AFTER_MAPPING;
import static com.example.entity_examples.news.NewsRequestExamples.VALID_CREATE_SPORT_NEWS_REQUEST;
import static com.example.entity_examples.news.NewsRequestExamples.VALID_UPDATE_SPORT_NEWS_REQUEST;
import static com.example.entity_examples.news.NewsResponseExamples.CREATE_SPORT_NEWS_RESPONSE;
import static com.example.entity_examples.news.NewsResponseExamples.FULL_SPORT_NEWS_RESPONSE;
import static com.example.entity_examples.news.NewsResponseExamples.SHORT_SPORT_NEWS_RESPONSE_LIST;
import static com.example.entity_examples.news.NewsResponseExamples.UPDATE_SPORT_NEWS_RESPONSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {NewsFacade.class, NewsFacadeTest.NewsFacadeTestConfiguration.class})
class NewsFacadeTest {

    @ComponentScan("com.example.mapper")
    static class NewsFacadeTestConfiguration {
    }

    @Autowired
    private NewsFacade facade;

    @MockBean
    private NewsService service;

    @Test
    void findAll() {
        when(service.findAll(SIMPLE_NEWS_FILTER)).thenReturn(NEWS_WITH_COMMENTS_COUNT);
        List<ShortNewsResponse> actual = facade.findAll(SIMPLE_NEWS_FILTER);

        assertEquals(SHORT_SPORT_NEWS_RESPONSE_LIST, actual);
        verify(service, times(1)).findAll(SIMPLE_NEWS_FILTER);
    }

    @Test
    void findById() {
        when(service.findById(1L)).thenReturn(SPORT_NEWS);
        FullNewsResponse actual = facade.findById(1L);

        assertEquals(FULL_SPORT_NEWS_RESPONSE, actual);
        verify(service, times(1)).findById(1L);
    }

    @Test
    void save() {
        when(service.save(1L, 1L, SPORT_NEWS_AFTER_MAPPING)).thenReturn(SPORT_NEWS);
        UpdateNewsResponse actual = facade.save(VALID_CREATE_SPORT_NEWS_REQUEST);

        assertEquals(CREATE_SPORT_NEWS_RESPONSE, actual);
        verify(service, times(1)).save(1L, 1L, SPORT_NEWS_AFTER_MAPPING);
    }

    @Test
    void update() {
        when(service.updateById(1L, UPDATE_SPORT_NEWS_AFTER_MAPPING)).thenReturn(UPDATED_SPORT_NEWS);
        UpdateNewsResponse actual = facade.updateById(1L, VALID_UPDATE_SPORT_NEWS_REQUEST);

        assertEquals(UPDATE_SPORT_NEWS_RESPONSE, actual);
        verify(service, times(1)).updateById(1L, UPDATE_SPORT_NEWS_AFTER_MAPPING);
    }

}
