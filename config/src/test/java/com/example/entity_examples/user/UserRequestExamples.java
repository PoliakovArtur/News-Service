package com.example.entity_examples.user;

import com.example.dto.user.request.CreateUserRequest;
import com.example.dto.user.request.UpdateUserRequest;
import com.example.filter.impl.UserFilter;

public class UserRequestExamples {

    public final static CreateUserRequest VALID_CREATE_USER_REQUEST = CreateUserRequest.builder()
            .name("John Doe")
            .age(23)
            .email("example.example@example.com")
            .aboutMe("Like animals")
            .build();

    public final static CreateUserRequest INVALID_CREATE_USER_REQUEST = CreateUserRequest.builder()
            .name("John Doe")
            .age(23)
            .email("exampleexample.com")
            .aboutMe("Like animals")
            .build();

    public final static UpdateUserRequest VALID_UPDATE_USER_REQUEST = UpdateUserRequest.builder()
            .aboutMe("Like cats")
            .build();

    public final static UserFilter SIMPLE_USER_FILTER = UserFilter.builder()
            .pageNumber(0)
            .pageSize(1)
            .desc(false)
            .build();
}
