package com.example.controller;

import com.example.config.MockMvcTest;
import com.example.facade.UserFacade;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.example.entity_examples.user.UserJsonExamples.INVALID_UPDATE_USER_JSON;
import static com.example.entity_examples.user.UserJsonExamples.VALID_UPDATE_USER_JSON;
import static com.example.entity_examples.user.UserRequestExamples.INVALID_CREATE_JOHN_DOE_REQUEST;
import static com.example.entity_examples.user.UserRequestExamples.SIMPLE_USER_FILTER;
import static com.example.entity_examples.user.UserRequestExamples.VALID_CREATE_JOHN_DOE_REQUEST;
import static com.example.entity_examples.user.UserRequestExamples.VALID_UPDATE_JOHN_DOE_REQUEST;
import static com.example.entity_examples.user.UserResponseExamples.CREATED_JOHN_DOE_RESPONSE;
import static com.example.entity_examples.user.UserResponseExamples.UPDATED_JOHN_DOE_RESPONSE;
import static com.example.entity_examples.user.UserResponseExamples.USER_INFO_RESPONSE_LIST;
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

@WebMvcTest(UserController.class)
class UserControllerTest extends MockMvcTest {

    @MockBean
    private UserFacade facade;

    @Test
    void findById_shouldReturn200() throws Exception {
        when(facade.findById(1L)).thenReturn(CREATED_JOHN_DOE_RESPONSE);

        expectBodyFromGet(createUrl(USER_EP, ID), OK, CREATED_JOHN_DOE_RESPONSE);
        verify(facade, times(1)).findById(1L);
    }

    @Test
    void findByInvalidId_shouldReturn400() throws Exception {
        expectStatusFromGet(createUrl(USER_EP, WRONG_ID), BAD_REQUEST);
        verify(facade, times(0)).findById(any());
    }

    @Test
    void findById_shouldReturn500() throws Exception {
        when(facade.findById(1L)).thenThrow(new InternalError());

        expectMessageFromGet(createUrl(USER_EP, ID), INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }

    @Test
    void findAll_shouldReturn200() throws Exception {
        when(facade.findAll(SIMPLE_USER_FILTER)).thenReturn(USER_INFO_RESPONSE_LIST);

        expectBodyFromGet(USER_EP, OK, USER_INFO_RESPONSE_LIST, "pageSize", "1", "pageNumber", "0");
        verify(facade, times(1)).findAll(SIMPLE_USER_FILTER);
    }

    @Test
    void findAllWithInvalidPageSize_shouldReturn400() throws Exception {
        expectMessageFromGet(USER_EP, BAD_REQUEST, "Размер страницы должен быть больше 0",
                "pageSize", "-1", "pageNumber", "0");
        verify(facade, times(0)).findAll(any());
    }

    @Test
    void findAll_shouldReturn500() throws Exception {
        when(facade.findAll(SIMPLE_USER_FILTER)).thenThrow(new InternalError());
        expectMessageFromGet(USER_EP, INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера",
                "pageSize", "1", "pageNumber", "0");
    }

    @Test
    void save_shouldReturn201() throws Exception {
        when(facade.save(VALID_CREATE_JOHN_DOE_REQUEST)).thenReturn(CREATED_JOHN_DOE_RESPONSE);

        expectBodyFromPost(USER_EP, VALID_CREATE_JOHN_DOE_REQUEST, CREATED, CREATED_JOHN_DOE_RESPONSE);
        verify(facade, times(1)).save(VALID_CREATE_JOHN_DOE_REQUEST);
    }

    @Test
    void saveWithInvalidEmail_shouldReturn400() throws Exception {
        expectMessageFromPost(USER_EP, INVALID_CREATE_JOHN_DOE_REQUEST, BAD_REQUEST, "Введен невалидный email");
        verify(facade, times(0)).save(any());
    }

    @Test
    void save_shouldReturn500() throws Exception {
        when(facade.save(VALID_CREATE_JOHN_DOE_REQUEST)).thenThrow(new InternalError());

        expectMessageFromPost(USER_EP, VALID_CREATE_JOHN_DOE_REQUEST, INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }

    @Test
    void updateById_shouldReturn200() throws Exception {
        when(facade.updateById(1L, VALID_UPDATE_JOHN_DOE_REQUEST)).thenReturn(UPDATED_JOHN_DOE_RESPONSE);

        expectBodyFromPut(createUrl(USER_EP, ID), VALID_UPDATE_USER_JSON, OK, UPDATED_JOHN_DOE_RESPONSE);
        verify(facade, times(1)).updateById(1L, VALID_UPDATE_JOHN_DOE_REQUEST);
    }

    @Test
    void updateByIdWithInvalidAge_shouldReturn400() throws Exception {
        expectMessageFromPut(createUrl(USER_EP, ID), INVALID_UPDATE_USER_JSON, BAD_REQUEST, "Возраст не может быть меньше 1");
        verify(facade, times(0)).updateById(any(), any());
    }

    @Test
    void updateByIdWithInvalidId_shouldReturn400() throws Exception {
        expectStatusFromPut(createUrl(USER_EP, WRONG_ID), VALID_UPDATE_USER_JSON, BAD_REQUEST);
        verify(facade, times(0)).updateById(any(), any());
    }

    @Test
    void updateById_shouldReturn500() throws Exception {
        when(facade.updateById(1L, VALID_UPDATE_JOHN_DOE_REQUEST)).thenThrow(new InternalError());

        expectMessageFromPut(createUrl(USER_EP, ID), VALID_UPDATE_USER_JSON, INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }

    @Test
    void deleteById_shouldReturn204() throws Exception {
        expectStatusFromDelete(createUrl(USER_EP, ID), NO_CONTENT);
        verify(facade, times(1)).deleteById(1L);
    }

    @Test
    void deleteByIdWithWrongId_shouldReturn400() throws Exception {
        expectStatusFromDelete(createUrl(USER_EP, WRONG_ID), BAD_REQUEST);
        verify(facade, times(0)).deleteById(any());
    }

    @Test
    void deleteById_shouldReturn500() throws Exception {
        doThrow(InternalError.class).when(facade).deleteById(1L);

        expectMessageFromDelete(createUrl(USER_EP, ID), INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }

}
