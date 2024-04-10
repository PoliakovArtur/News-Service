package com.example.controller;

import com.example.config.MockMvcTest;
import com.example.facade.NewsFacade;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.example.entity_examples.news.NewsDbExamples.SIMPLE_NEWS_FILTER;
import static com.example.entity_examples.news.NewsJsonExamples.INVALID_UPDATE_NEWS_JSON;
import static com.example.entity_examples.news.NewsJsonExamples.VALID_UPDATE_NEWS_JSON;
import static com.example.entity_examples.news.NewsRequestExamples.INVALID_CREATE_NEWS_REQUEST;
import static com.example.entity_examples.news.NewsRequestExamples.VALID_CREATE_NEWS_REQUEST;
import static com.example.entity_examples.news.NewsRequestExamples.VALID_UPDATE_NEWS_REQUEST;
import static com.example.entity_examples.news.NewsResponseExamples.FULL_NEWS_RESPONSE;
import static com.example.entity_examples.news.NewsResponseExamples.SHORT_NEWS_RESPONSE_LIST;
import static com.example.entity_examples.news.NewsResponseExamples.UPDATE_NEWS_RESPONSE;
import static com.example.util.MockMvcUtils.createUrl;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@WebMvcTest(NewsController.class)
class NewsControllerTest extends MockMvcTest {

    @MockBean
    private NewsFacade facade;

    @Test
    void findById_shouldReturn200() throws Exception {
        when(facade.findById(1L)).thenReturn(FULL_NEWS_RESPONSE);

        expectBodyFromGet(createUrl(NEWS_EP, ID), OK, FULL_NEWS_RESPONSE);
        verify(facade, times(1)).findById(1L);
    }

    @Test
    void findByInvalidId_shouldReturn400() throws Exception {
        expectStatusFromGet(createUrl(NEWS_EP, WRONG_ID), BAD_REQUEST);
        verify(facade, times(0)).findById(any());
    }

    @Test
    void findById_shouldReturn500() throws Exception {
        when(facade.findById(1L)).thenThrow(new InternalError());

        expectMessageFromGet(createUrl(NEWS_EP, ID), INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }

    @Test
    void findAll_shouldReturn200() throws Exception {
        when(facade.findAll(SIMPLE_NEWS_FILTER)).thenReturn(SHORT_NEWS_RESPONSE_LIST);

        expectBodyFromGet(NEWS_EP, OK, SHORT_NEWS_RESPONSE_LIST, "pageSize", "1", "pageNumber", "0");
        verify(facade, times(1)).findAll(SIMPLE_NEWS_FILTER);
    }

    @Test
    void findAllWithInvalidPageSize_shouldReturn400() throws Exception {
        expectMessageFromGet(NEWS_EP, BAD_REQUEST, "Размер страницы должен быть больше 0",
                "pageSize", "-1", "pageNumber", "0");
        verify(facade, times(0)).findAll(any());
    }

    @Test
    void findAll_shouldReturn500() throws Exception {
        when(facade.findAll(SIMPLE_NEWS_FILTER)).thenThrow(new InternalError());
        expectMessageFromGet(NEWS_EP, INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера",
                "pageSize", "1", "pageNumber", "0");
    }

    @Test
    void save_shouldReturn201() throws Exception {
        when(facade.save(VALID_CREATE_NEWS_REQUEST)).thenReturn(UPDATE_NEWS_RESPONSE);

        expectBodyFromPost(NEWS_EP, VALID_CREATE_NEWS_REQUEST, CREATED, UPDATE_NEWS_RESPONSE);
        verify(facade, times(1)).save(VALID_CREATE_NEWS_REQUEST);
    }

    @Test
    void saveWithEmptyTitle_shouldReturn400() throws Exception {
        expectMessageFromPost(NEWS_EP, INVALID_CREATE_NEWS_REQUEST, BAD_REQUEST,
                "Нельзя создать новость с пустым заголовком");
        verify(facade, times(0)).save(any());
    }

    @Test
    void save_shouldReturn500() throws Exception {
        when(facade.save(VALID_CREATE_NEWS_REQUEST)).thenThrow(new InternalError());

        expectMessageFromPost(NEWS_EP, VALID_CREATE_NEWS_REQUEST, INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }

    @Test
    void updateById_shouldReturn200() throws Exception {
        when(facade.updateById(1L, VALID_UPDATE_NEWS_REQUEST)).thenReturn(UPDATE_NEWS_RESPONSE);

        expectBodyFromPut(createUrl(NEWS_EP, ID), VALID_UPDATE_NEWS_JSON, OK, UPDATE_NEWS_RESPONSE);
        verify(facade, times(1)).updateById(1L, VALID_UPDATE_NEWS_REQUEST);
    }

    @Test
    void updateByIdWithBlankContent_shouldReturn400() throws Exception {
        expectMessageFromPut(createUrl(NEWS_EP, ID), INVALID_UPDATE_NEWS_JSON, BAD_REQUEST,
                "Нельзя изменить содержание новости на пустое значение");
        verify(facade, times(0)).updateById(any(), any());
    }

    @Test
    void updateByIdWithInvalidId_shouldReturn400() throws Exception {
        expectStatusFromPut(createUrl(NEWS_EP, WRONG_ID), VALID_UPDATE_NEWS_JSON, BAD_REQUEST);
        verify(facade, times(0)).updateById(any(), any());
    }

    @Test
    void updateById_shouldReturn500() throws Exception {
        when(facade.updateById(1L, VALID_UPDATE_NEWS_REQUEST)).thenThrow(new InternalError());

        expectMessageFromPut(createUrl(NEWS_EP, ID), VALID_UPDATE_NEWS_JSON, INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }

    @Test
    void deleteById_shouldReturn204() throws Exception {
        expectStatusFromDelete(createUrl(NEWS_EP, ID), NO_CONTENT, "userId", "2");
        verify(facade, times(1)).deleteById(2L, 1L);
    }

    @Test
    void deleteByIdWithWrongId_shouldReturn400() throws Exception {
        expectStatusFromDelete(createUrl(NEWS_EP, WRONG_ID), BAD_REQUEST, "userId", "1");
        verify(facade, times(0)).deleteById(any(), any());
    }

    @Test
    void deleteById_shouldReturn500() throws Exception {
        doThrow(InternalError.class).when(facade).deleteById(2L, 1L);

        expectMessageFromDelete(createUrl(NEWS_EP, ID), INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }
}
