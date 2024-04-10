package com.example.facade;

import com.example.dto.comment.response.CommentResponse;
import com.example.mapper.CommentMapper;
import com.example.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.entity_examples.comment.CommentDbExamples.CREATE_REQUEST_COMMENT_AFTER_MAPPING;
import static com.example.entity_examples.comment.CommentDbExamples.SPORT_NEWS_COMMENT;
import static com.example.entity_examples.comment.CommentDbExamples.SPORT_NEWS_COMMENT_LIST;
import static com.example.entity_examples.comment.CommentDbExamples.UPDATED_SPORT_NEWS_COMMENT;
import static com.example.entity_examples.comment.CommentDbExamples.UPDATE_REQUEST_COMMENT_AFTER_MAPPING;
import static com.example.entity_examples.comment.CommentRequestExamples.VALID_CREATE_COMMENT_REQUEST;
import static com.example.entity_examples.comment.CommentRequestExamples.VALID_UPDATE_COMMENT_REQUEST;
import static com.example.entity_examples.comment.CommentResponseExamples.SPORT_NEWS_COMMENTS_RESPONSE_LIST;
import static com.example.entity_examples.comment.CommentResponseExamples.SPORT_NEWS_COMMENT_RESPONSE;
import static com.example.entity_examples.comment.CommentResponseExamples.UPDATED_COMMENT_RESPONSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentFacadeTest {

    @InjectMocks
    private CommentFacade facade;

    @Mock
    private CommentService service;

    @Spy
    private CommentMapper mapper = Mappers.getMapper(CommentMapper.class);

    @Test
    void findByNews() {
        when(service.findByNews(1L)).thenReturn(SPORT_NEWS_COMMENT_LIST);
        List<CommentResponse> actual = facade.findByNews(1L);

        assertEquals(SPORT_NEWS_COMMENTS_RESPONSE_LIST, actual);
        verify(service, times(1)).findByNews(1L);
    }

    @Test
    void findById() {
        when(service.findById(1L)).thenReturn(SPORT_NEWS_COMMENT);
        CommentResponse actual = facade.findById(1L);

        assertEquals(SPORT_NEWS_COMMENT_RESPONSE, actual);
        verify(service, times(1)).findById(1L);
    }

    @Test
    void save() {
        when(service.save(1L, 1L, CREATE_REQUEST_COMMENT_AFTER_MAPPING)).thenReturn(SPORT_NEWS_COMMENT);
        CommentResponse actual = facade.save(VALID_CREATE_COMMENT_REQUEST);

        assertEquals(SPORT_NEWS_COMMENT_RESPONSE, actual);
        verify(service, times(1)).save(1L, 1L, CREATE_REQUEST_COMMENT_AFTER_MAPPING);
    }

    @Test
    void updateById() {
        when(service.updateById(1L, UPDATE_REQUEST_COMMENT_AFTER_MAPPING)).thenReturn(UPDATED_SPORT_NEWS_COMMENT);
        CommentResponse actual = facade.updateById(1L, VALID_UPDATE_COMMENT_REQUEST);

        assertEquals(UPDATED_COMMENT_RESPONSE, actual);
        verify(service, times(1)).updateById(1L, UPDATE_REQUEST_COMMENT_AFTER_MAPPING);
    }
}
