package com.example.facade;

import com.example.dto.user.response.UserResponse;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.entity_examples.user.UserDbExamples.FIRST_PAGE;
import static com.example.entity_examples.user.UserDbExamples.JOHN_DOE;
import static com.example.entity_examples.user.UserDbExamples.JOHN_DOE_AFTER_MAPPING;
import static com.example.entity_examples.user.UserDbExamples.UPDATED_JOHN_DOE;
import static com.example.entity_examples.user.UserDbExamples.UPDATE_JOHN_DOE_AFTER_MAPPING;
import static com.example.entity_examples.user.UserRequestExamples.SIMPLE_USER_FILTER;
import static com.example.entity_examples.user.UserRequestExamples.VALID_CREATE_JOHN_DOE_REQUEST;
import static com.example.entity_examples.user.UserRequestExamples.VALID_UPDATE_JOHN_DOE_REQUEST;
import static com.example.entity_examples.user.UserResponseExamples.CREATED_JOHN_DOE_RESPONSE;
import static com.example.entity_examples.user.UserResponseExamples.UPDATED_JOHN_DOE_RESPONSE;
import static com.example.entity_examples.user.UserResponseExamples.USER_INFO_RESPONSE_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserFacadeTest {

    @InjectMocks
    private UserFacade facade;

    @Mock
    private UserService service;

    @Spy
    private UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    void findAll() {
        when(service.findAll(SIMPLE_USER_FILTER)).thenReturn(FIRST_PAGE);
        List<UserResponse> actual = facade.findAll(SIMPLE_USER_FILTER);

        assertEquals(USER_INFO_RESPONSE_LIST, actual);
        verify(service, times(1)).findAll(SIMPLE_USER_FILTER);
    }

    @Test
    void findById() {
        when(service.findById(1L)).thenReturn(JOHN_DOE);
        UserResponse actual = facade.findById(1L);

        assertEquals(CREATED_JOHN_DOE_RESPONSE, actual);
        verify(service, times(1)).findById(1L);
    }

    @Test
    void save() {
        when(service.save(JOHN_DOE_AFTER_MAPPING)).thenReturn(JOHN_DOE);
        UserResponse actual = facade.save(VALID_CREATE_JOHN_DOE_REQUEST);

        assertEquals(CREATED_JOHN_DOE_RESPONSE, actual);
        verify(service, times(1)).save(JOHN_DOE_AFTER_MAPPING);
    }

    @Test
    void update() {
        when(service.updateById(1L, UPDATE_JOHN_DOE_AFTER_MAPPING)).thenReturn(UPDATED_JOHN_DOE);
        UserResponse actual = facade.updateById(1L, VALID_UPDATE_JOHN_DOE_REQUEST);

        assertEquals(UPDATED_JOHN_DOE_RESPONSE, actual);
        verify(service, times(1)).updateById(1L, UPDATE_JOHN_DOE_AFTER_MAPPING);
    }
}
