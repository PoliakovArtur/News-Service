package com.example.controller;

import com.example.config.MockMvcTest;
import com.example.facade.CategoryFacade;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.example.entity_examples.category.CategoryJsonExamples.INVALID_UPDATE_CATEGORY_JSON;
import static com.example.entity_examples.category.CategoryJsonExamples.VALID_UPDATE_CATEGORY_JSON;
import static com.example.entity_examples.category.CategoryRequestExamples.INVALID_CREATE_CATEGORY_REQUEST;
import static com.example.entity_examples.category.CategoryRequestExamples.SIMPLE_CATEGORY_FILTER;
import static com.example.entity_examples.category.CategoryRequestExamples.VALID_CREATE_CATEGORY_REQUEST;
import static com.example.entity_examples.category.CategoryRequestExamples.VALID_UPDATE_CATEGORY_REQUEST;
import static com.example.entity_examples.category.CategoryResponseExamples.FULL_CATEGORY_RESPONSE;
import static com.example.entity_examples.category.CategoryResponseExamples.SHORT_CATEGORY_RESPONSE_LIST;
import static com.example.entity_examples.category.CategoryResponseExamples.SHORT_CATEGORY_RESPONSE;
import static com.example.entity_examples.category.CategoryResponseExamples.SHORT_UPDATED_CATEGORY_RESPONSE;
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

@WebMvcTest(CategoryController.class)
class CategoryControllerTest extends MockMvcTest {

    @MockBean
    private CategoryFacade facade;

    @Test
    void findById_shouldReturn200() throws Exception {
        when(facade.findById(1L)).thenReturn(FULL_CATEGORY_RESPONSE);

        expectBodyFromGet(createUrl(CATEGORY_EP, ID), OK, FULL_CATEGORY_RESPONSE);
        verify(facade, times(1)).findById(1L);
    }

    @Test
    void findByInvalidId_shouldReturn400() throws Exception {
        expectStatusFromGet(createUrl(CATEGORY_EP, WRONG_ID), BAD_REQUEST);
        verify(facade, times(0)).findById(any());
    }

    @Test
    void findById_shouldReturn500() throws Exception {
        when(facade.findById(1L)).thenThrow(new InternalError());

        expectMessageFromGet(createUrl(CATEGORY_EP, ID), INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }

    @Test
    void findAll_shouldReturn200() throws Exception {
        when(facade.findAll(SIMPLE_CATEGORY_FILTER)).thenReturn(SHORT_CATEGORY_RESPONSE_LIST);

        expectBodyFromGet(CATEGORY_EP, OK, SHORT_CATEGORY_RESPONSE_LIST, "pageSize", "1", "pageNumber", "0");
        verify(facade, times(1)).findAll(SIMPLE_CATEGORY_FILTER);
    }

    @Test
    void findAllWithInvalidPageSize_shouldReturn400() throws Exception {
        expectMessageFromGet(CATEGORY_EP, BAD_REQUEST, "Размер страницы должен быть больше 0",
                "pageSize", "-1", "pageNumber", "0");
        verify(facade, times(0)).findAll(any());
    }

    @Test
    void findAll_shouldReturn500() throws Exception {
        when(facade.findAll(SIMPLE_CATEGORY_FILTER)).thenThrow(new InternalError());
        expectMessageFromGet(CATEGORY_EP, INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера",
                "pageSize", "1", "pageNumber", "0");
    }

    @Test
    void save_shouldReturn201() throws Exception {
        when(facade.save(VALID_CREATE_CATEGORY_REQUEST)).thenReturn(SHORT_CATEGORY_RESPONSE);

        expectBodyFromPost(CATEGORY_EP, VALID_CREATE_CATEGORY_REQUEST, CREATED, SHORT_CATEGORY_RESPONSE);
        verify(facade, times(1)).save(VALID_CREATE_CATEGORY_REQUEST);
    }

    @Test
    void saveWithEmptyTitle_shouldReturn400() throws Exception {
        expectMessageFromPost(CATEGORY_EP, INVALID_CREATE_CATEGORY_REQUEST, BAD_REQUEST,
                "Нельзя создать категорию с пустым заголовком");
        verify(facade, times(0)).save(any());
    }

    @Test
    void save_shouldReturn500() throws Exception {
        when(facade.save(VALID_CREATE_CATEGORY_REQUEST)).thenThrow(new InternalError());

        expectMessageFromPost(CATEGORY_EP, VALID_CREATE_CATEGORY_REQUEST, INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }

    @Test
    void updateById_shouldReturn200() throws Exception {
        when(facade.updateById(1L, VALID_UPDATE_CATEGORY_REQUEST)).thenReturn(SHORT_UPDATED_CATEGORY_RESPONSE);

        expectBodyFromPut(createUrl(CATEGORY_EP, ID), VALID_UPDATE_CATEGORY_JSON, OK, SHORT_UPDATED_CATEGORY_RESPONSE);
        verify(facade, times(1)).updateById(1L, VALID_UPDATE_CATEGORY_REQUEST);
    }

    @Test
    void updateByIdWithBlankTitle_shouldReturn400() throws Exception {
        expectMessageFromPut(createUrl(CATEGORY_EP, ID), INVALID_UPDATE_CATEGORY_JSON, BAD_REQUEST,
                "Нельзя изменить заголовок категории на пустое значение");
        verify(facade, times(0)).updateById(any(), any());
    }

    @Test
    void updateByIdWithInvalidId_shouldReturn400() throws Exception {
        expectStatusFromPut(createUrl(CATEGORY_EP, WRONG_ID), VALID_UPDATE_CATEGORY_JSON, BAD_REQUEST);
        verify(facade, times(0)).updateById(any(), any());
    }

    @Test
    void updateById_shouldReturn500() throws Exception {
        when(facade.updateById(1L, VALID_UPDATE_CATEGORY_REQUEST)).thenThrow(new InternalError());

        expectMessageFromPut(createUrl(CATEGORY_EP, ID), VALID_UPDATE_CATEGORY_JSON, INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }

    @Test
    void deleteById_shouldReturn204() throws Exception {
        expectStatusFromDelete(createUrl(CATEGORY_EP, ID), NO_CONTENT, "userId", "2");
        verify(facade, times(1)).deleteById(2L, 1L);
    }

    @Test
    void deleteByIdWithWrongId_shouldReturn400() throws Exception {
        expectStatusFromDelete(createUrl(CATEGORY_EP, WRONG_ID), BAD_REQUEST, "userId", "1");
        verify(facade, times(0)).deleteById(any(), any());
    }

    @Test
    void deleteById_shouldReturn500() throws Exception {
        doThrow(InternalError.class).when(facade).deleteById(2L, 1L);

        expectMessageFromDelete(createUrl(CATEGORY_EP, ID), INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }
}
