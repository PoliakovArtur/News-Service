package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.util.MockMvcUtils.asJsonString;
import static java.nio.charset.StandardCharsets.UTF_8;
import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class MockMvcTest {

    @Autowired
    protected MockMvc mockMvc;

    protected void expectBodyFromGet(String url, HttpStatus status, Object expectedResponse) throws Exception {
        String result = mockMvc.perform(get(url))
                .andExpect(status().is(status.value()))
                .andReturn()
                .getResponse()
                .getContentAsString(UTF_8);

        assertJsonEquals(asJsonString(expectedResponse), result);
    }

    protected void expectMessageFromGet(String url, HttpStatus status, String message) throws Exception {
        mockMvc.perform(get(url))
                .andExpectAll(
                        status().is(status.value()),
                        jsonPath("$.message").value(message));
    }

    protected void expectStatusFromGet(String url, HttpStatus status) throws Exception {
        mockMvc.perform(get(url))
                .andExpectAll(status().is(status.value()));
    }
}
