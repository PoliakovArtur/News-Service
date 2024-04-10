package com.example.entity_examples.user;

import com.example.dto.user.response.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

public class UserResponseExamples {

    public final static UserResponse CREATED_JOHN_DOE_RESPONSE = UserResponse.builder()
            .id(1L)
            .name("John Doe")
            .age(23)
            .email("example.example@example.com")
            .aboutMe("Like animals")
            .registrationDate(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .build();

    public final static List<UserResponse> USER_INFO_RESPONSE_LIST = List.of(CREATED_JOHN_DOE_RESPONSE);

    public final static UserResponse UPDATED_JOHN_DOE_RESPONSE = UserResponse.builder()
            .id(1L)
            .name("John Doe")
            .age(23)
            .email("example.example@example.com")
            .aboutMe("Like cats")
            .registrationDate(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .build();

}
