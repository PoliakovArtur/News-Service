package com.example.entity_examples.user;

import com.example.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class UserDbExamples {

    public final static User USER = User.builder()
            .id(1L)
            .name("John Doe")
            .age(23)
            .email("example.example@example.com")
            .aboutMe("Like animals")
            .registrationDate(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .build();

    public final static User USER_AFTER_MAPPING = User.builder()
            .name("John Doe")
            .age(23)
            .email("example.example@example.com")
            .aboutMe("Like animals")
            .build();

    public final static User UPDATE_USER_AFTER_MAPPING = User.builder()
            .aboutMe("Like cats")
            .build();

    public final static User UPDATED_USER = User.builder()
            .id(1L)
            .name("John Doe")
            .age(23)
            .email("example.example@example.com")
            .aboutMe("Like cats")
            .registrationDate(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .build();
    
    public final static List<User> USER_PAGE = List.of(USER);
}
