package com.example.entity_examples.category;

import com.example.dto.category.response.FullCategoryResponse;
import com.example.dto.category.response.ShortCategoryResponse;

import java.time.LocalDateTime;
import java.util.List;

public class CategoryResponseExamples {

    public final static FullCategoryResponse FULL_SPORT_CATEGORY_RESPONSE = FullCategoryResponse.builder()
            .id(1L)
            .title("Sport")
            .description("News about Sport")
            .author("John Doe")
            .createAt(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .updateAt(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .build();

    public final static ShortCategoryResponse SHORT_SPORT_CATEGORY_RESPONSE = ShortCategoryResponse.builder()
            .id(1L)
            .title("Sport")
            .description("News about Sport")
            .author("John Doe")
            .createAt(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .build();

    public final static List<ShortCategoryResponse> SHORT_CATEGORY_RESPONSE_LIST = List.of(SHORT_SPORT_CATEGORY_RESPONSE);

    public final static ShortCategoryResponse SHORT_UPDATED_SPORT_CATEGORY_RESPONSE = ShortCategoryResponse.builder()
            .id(1L)
            .title("Sport")
            .description("News about sport and athletes")
            .author("John Doe")
            .createAt(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .build();
}
