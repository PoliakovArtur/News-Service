package com.example.controller;

import com.example.MockMvcTest;
import com.example.facade.UserFacade;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.example.entity_examples.UserExamples.USER_INFO_DTO;
import static com.example.util.MockMvcUtils.createUrl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(UserController.class)
class UserControllerTest extends MockMvcTest {

    @InjectMocks
    private UserController controller;
    @MockBean
    private UserFacade facade;
    @Value("${endpoint.user}")
    private String userBaseEp;
    private final static Long ID = 1L;
    private final static String ID_STR = "1";
    private final static String WRONG_ID_STR = "zzzz";

    @Test
    void findById_shouldReturn200() throws Exception {
        when(facade.findById(ID)).thenReturn(USER_INFO_DTO);
        expectBodyFromGet(createUrl(userBaseEp, ID_STR), OK, USER_INFO_DTO);
        verify(facade, times(1)).findById(ID);
    }

    @Test
    void findById_shouldReturn400() throws Exception {
        expectStatusFromGet(createUrl(userBaseEp, WRONG_ID_STR), BAD_REQUEST);
        verify(facade, times(0)).findById(any());
    }

}
