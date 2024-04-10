package com.example.controller;

import com.example.config.MockMvcTest;
import com.example.facade.CommentFacade;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.example.entity_examples.comment.CommentRequestExamples.INVALID_CREATE_COMMENT_REQUEST;
import static com.example.entity_examples.comment.CommentRequestExamples.INVALID_UPDATE_COMMENT_REQUEST;
import static com.example.entity_examples.comment.CommentRequestExamples.VALID_CREATE_COMMENT_REQUEST;
import static com.example.entity_examples.comment.CommentRequestExamples.VALID_UPDATE_COMMENT_REQUEST;
import static com.example.entity_examples.comment.CommentResponseExamples.COMMENT_RESPONSE;
import static com.example.entity_examples.comment.CommentResponseExamples.COMMENT_RESPONSE_LIST;
import static com.example.entity_examples.comment.CommentResponseExamples.UPDATED_COMMENT_RESPONSE;
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

@WebMvcTest(CommentController.class)
public class CommentControllerTest extends MockMvcTest {
    
    @MockBean
    private CommentFacade facade;

    @Test
    void findById_shouldReturn200() throws Exception {
        when(facade.findById(1L)).thenReturn(COMMENT_RESPONSE);

        expectBodyFromGet(createUrl(COMMENT_EP, ID), OK, COMMENT_RESPONSE);
        verify(facade, times(1)).findById(1L);
    }

    @Test
    void findByInvalidId_shouldReturn400() throws Exception {
        expectStatusFromGet(createUrl(COMMENT_EP, WRONG_ID), BAD_REQUEST);
        verify(facade, times(0)).findById(any());
    }

    @Test
    void findById_shouldReturn500() throws Exception {
        when(facade.findById(1L)).thenThrow(new InternalError());

        expectMessageFromGet(createUrl(COMMENT_EP, ID), INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }

    @Test
    void findByNews_shouldReturn200() throws Exception {
        when(facade.findByNews(1L)).thenReturn(COMMENT_RESPONSE_LIST);

        expectBodyFromGet(COMMENT_EP, OK, COMMENT_RESPONSE_LIST, "newsId", "1");
        verify(facade, times(1)).findByNews(1L);
    }

    @Test
    void findByNewsWithInvalidNewsId_shouldReturn400() throws Exception {
        expectStatusFromGet(COMMENT_EP, BAD_REQUEST, "newsId", "asdfd");
        verify(facade, times(0)).findByNews(any());
    }

    @Test
    void findByNews_shouldReturn500() throws Exception {
        when(facade.findByNews(1L)).thenThrow(new InternalError());
        expectMessageFromGet(COMMENT_EP, INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера", "newsId", "1");
    }

    @Test
    void save_shouldReturn201() throws Exception {
        when(facade.save(VALID_CREATE_COMMENT_REQUEST)).thenReturn(COMMENT_RESPONSE);

        expectBodyFromPost(COMMENT_EP, VALID_CREATE_COMMENT_REQUEST, CREATED, COMMENT_RESPONSE);
        verify(facade, times(1)).save(VALID_CREATE_COMMENT_REQUEST);
    }

    @Test
    void saveWithEmptyTitle_shouldReturn400() throws Exception {
        expectMessageFromPost(COMMENT_EP, INVALID_CREATE_COMMENT_REQUEST, BAD_REQUEST,
                "Нельзя создать пустой комментарий");
        verify(facade, times(0)).save(any());
    }

    @Test
    void save_shouldReturn500() throws Exception {
        when(facade.save(VALID_CREATE_COMMENT_REQUEST)).thenThrow(new InternalError());

        expectMessageFromPost(COMMENT_EP, VALID_CREATE_COMMENT_REQUEST, INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }

    @Test
    void updateById_shouldReturn200() throws Exception {
        when(facade.updateById(1L, VALID_UPDATE_COMMENT_REQUEST)).thenReturn(UPDATED_COMMENT_RESPONSE);

        expectBodyFromPut(createUrl(COMMENT_EP, ID), VALID_UPDATE_COMMENT_REQUEST, OK, UPDATED_COMMENT_RESPONSE);
        verify(facade, times(1)).updateById(1L, VALID_UPDATE_COMMENT_REQUEST);
    }

    @Test
    void updateByIdWithBlankContent_shouldReturn400() throws Exception {
        expectMessageFromPut(createUrl(COMMENT_EP, ID), INVALID_UPDATE_COMMENT_REQUEST, BAD_REQUEST,
                "Нельзя изменить содержимое комментария на пустое значение");
        verify(facade, times(0)).updateById(any(), any());
    }

    @Test
    void updateByIdWithInvalidId_shouldReturn400() throws Exception {
        expectStatusFromPut(createUrl(COMMENT_EP, WRONG_ID), VALID_UPDATE_COMMENT_REQUEST, BAD_REQUEST);
        verify(facade, times(0)).updateById(any(), any());
    }

    @Test
    void updateById_shouldReturn500() throws Exception {
        when(facade.updateById(1L, VALID_UPDATE_COMMENT_REQUEST)).thenThrow(new InternalError());

        expectMessageFromPut(createUrl(COMMENT_EP, ID), VALID_UPDATE_COMMENT_REQUEST, INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }

    @Test
    void deleteById_shouldReturn204() throws Exception {
        expectStatusFromDelete(createUrl(COMMENT_EP, ID), NO_CONTENT, "userId", "2");
        verify(facade, times(1)).deleteById(2L, 1L);
    }

    @Test
    void deleteByIdWithWrongId_shouldReturn400() throws Exception {
        expectStatusFromDelete(createUrl(COMMENT_EP, WRONG_ID), BAD_REQUEST, "userId", "1");
        verify(facade, times(0)).deleteById(any(), any());
    }

    @Test
    void deleteById_shouldReturn500() throws Exception {
        doThrow(InternalError.class).when(facade).deleteById(2L, 1L);

        expectMessageFromDelete(createUrl(COMMENT_EP, ID), INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }
}
