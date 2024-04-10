package com.example.entity_examples.category;

import com.example.model.Category;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.entity_examples.user.UserDbExamples.JANE_DOE;
import static com.example.entity_examples.user.UserDbExamples.JOHN_DOE;

public class CategoryDbExamples {

    public final static Category SPORT_CATEGORY = Category.builder()
            .id(1L)
            .title("Sport")
            .description("News about Sport")
            .user(JOHN_DOE)
            .createAt(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .updateAt(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .build();

    public final static Category UPDATED_SPORT_CATEGORY = Category.builder()
            .id(1L)
            .title("Sport")
            .description("News about sport and athletes")
            .user(JOHN_DOE)
            .createAt(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .updateAt(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .build();

    public final static List<Category> FIRST_PAGE = List.of(SPORT_CATEGORY);

    public final static Category MUSIC_CATEGORY = Category.builder()
            .id(2L)
            .title("Music")
            .description("News about Music")
            .user(JANE_DOE)
            .createAt(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .updateAt(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .build();

    public final static Category SPORT_CATEGORY_AFTER_MAPPING = Category.builder()
            .title("Sport")
            .description("News about Sport")
            .build();

    public final static Category UPDATE_SPORT_CATEGORY_AFTER_MAPPING = Category.builder()
            .description("News about sport and athletes")
            .build();
}
