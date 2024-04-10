package com.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

public class MockMvcUtils {

    private final static ObjectMapper MAPPER =
            JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    private MockMvcUtils() {}

    public static String asJsonString(Object obj) throws JsonProcessingException {
        return MAPPER.writeValueAsString(obj);
    }

    public static <T> T jsonToObject(String json, Class<T> cl) throws JsonProcessingException {
        return MAPPER.readValue(json, cl);
    }

    public static <T> List<T> jsonToList(String jsonString, Class<T> valueType) throws JsonProcessingException {
        return MAPPER.readValue(jsonString, MAPPER.getTypeFactory().constructCollectionType(List.class, valueType));
    }

    public static String createUrl(String... components) {
        return String.join("/", components);
    }
}
