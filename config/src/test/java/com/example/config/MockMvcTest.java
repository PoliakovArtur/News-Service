package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.example.util.MockMvcUtils.asJsonString;
import static java.nio.charset.StandardCharsets.UTF_8;
import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class MockMvcTest {

    protected static final String ID = "1";
    protected static final String WRONG_ID = "zzzz";
    protected static final String USER_EP = "/api/v1/user";
    protected static final String CATEGORY_EP = "/api/v1/category";
    protected static final String NEWS_EP = "/api/v1/news";
    protected static final String COMMENT_EP = "/api/v1/comment";

    @Autowired
    protected MockMvc mockMvc;

    protected void expectBodyFromGet(String url, HttpStatus status, Object expectedResponse, String... params) throws Exception {
        expectBody(mockMvc.perform(getBuilderWithParams(
                get(url).contentType(MediaType.APPLICATION_JSON),
                params)), status, expectedResponse);
    }

    protected void expectMessageFromGet(String url, HttpStatus status, String message, String... params) throws Exception {
        expectMessage(mockMvc.perform(getBuilderWithParams(
                        get(url).contentType(MediaType.APPLICATION_JSON),
                        params)), status, message);
    }

    protected void expectStatusFromGet(String url, HttpStatus status, String... params) throws Exception {
        mockMvc.perform(getBuilderWithParams(
                        get(url), params))
                .andExpect(status().is(status.value()));
    }

    protected void expectBodyFromPost(String url, Object body, HttpStatus status, Object expectedResponse, String... params) throws Exception {
        expectBody(mockMvc.perform(getBuilderWithParams(
                        post(url).content((body instanceof String b) ? b : asJsonString(body)), params)
                        .contentType(MediaType.APPLICATION_JSON)), status, expectedResponse);
    }

    protected void expectMessageFromPost(String url, Object body, HttpStatus status, String message, String... params) throws Exception {
        expectMessage(mockMvc.perform(getBuilderWithParams(
                        post(url).content((body instanceof String b) ? b : asJsonString(body)), params)
                        .contentType(MediaType.APPLICATION_JSON)), status, message);
    }

    protected void expectBodyFromPut(String url, Object body, HttpStatus status, Object expectedResponse, String... params) throws Exception {
        expectBody(mockMvc.perform(getBuilderWithParams(
                        put(url).content((body instanceof String b) ? b : asJsonString(body)), params)
                        .contentType(MediaType.APPLICATION_JSON)), status, expectedResponse);
    }

    protected void expectMessageFromPut(String url, Object body, HttpStatus status, String message, String... params) throws Exception {
        expectMessage(mockMvc.perform(getBuilderWithParams(
                put(url).content((body instanceof String b) ? b : asJsonString(body)), params)
                .contentType(MediaType.APPLICATION_JSON)), status, message);
    }

    protected void expectStatusFromPut(String url, Object body, HttpStatus status, String... params) throws Exception {
        mockMvc.perform(getBuilderWithParams(
                put(url).content((body instanceof String b) ? b : asJsonString(body)), params)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(status.value()));
    }

    protected void expectStatusFromDelete(String url, HttpStatus status, String... params) throws Exception {
        mockMvc.perform(getBuilderWithParams(
                        delete(url), params))
                .andExpect(status().is(status.value()));
    }

    protected void expectMessageFromDelete(String url, HttpStatus status, String message, String... params) throws Exception {
        expectMessage(mockMvc.perform(getBuilderWithParams(
                delete(url).contentType(MediaType.APPLICATION_JSON),
                params)), status, message);
    }

    private void expectBody(ResultActions resultActions, HttpStatus status, Object expectedResponse) throws Exception {
        String result = resultActions
                .andExpect(status().is(status.value()))
                .andReturn()
                .getResponse()
                .getContentAsString(UTF_8);

        assertJsonEquals(asJsonString(expectedResponse), result);
    }

    private void expectMessage(ResultActions resultActions, HttpStatus status, String message) throws Exception {
        resultActions.andExpectAll(
                        status().is(status.value()),
                        jsonPath("$.message").value(message));
    }

    private MockHttpServletRequestBuilder getBuilderWithParams(MockHttpServletRequestBuilder builder, String... params) {
        int length = params.length;
        if ((length & 1) == 1) throw new IllegalArgumentException("Не у всех переданных параметров указаны значения");

        for (int i = 0; i < length; i += 2) {
            builder = builder.param(params[i], params[i + 1]);
        }
        return builder;
    }
}
