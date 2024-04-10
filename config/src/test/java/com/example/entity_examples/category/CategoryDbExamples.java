package com.example.entity_examples.category;

import com.example.model.Category;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.entity_examples.user.UserDbExamples.USER;

public class CategoryDbExamples {

    public final static Category CATEGORY = Category.builder()
            .id(1L)
            .title("Sport")
            .description("News about Sport")
            .user(USER)
            .createAt(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .updateAt(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .build();

    public final static Category UPDATED_CATEGORY = Category.builder()
            .id(1L)
            .title("Sport")
            .description("News about sport and athletes")
            .user(USER)
            .createAt(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .updateAt(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .build();

    public final static List<Category> CATEGORY_PAGE = List.of(CATEGORY);

    public final static Category CATEGORY_AFTER_MAPPING = Category.builder()
            .title("Sport")
            .description("News about Sport")
            .build();

    public final static Category UPDATE_CATEGORY_AFTER_MAPPING = Category.builder()
            .description("News about sport and athletes")
            .build();
}
