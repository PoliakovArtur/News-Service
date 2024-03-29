package com.example.entity_examples;

import com.example.dto.user.request.UpdateUserDto;
import com.example.dto.user.response.UserInfoDto;
import com.example.model.User;

import java.time.LocalDateTime;

public class UserExamples {

    private UserExamples() {}

    public final static User USER = User.builder()
            .id(1L)
            .name("John Doe")
            .age(22)
            .email("example.example@example.com")
            .aboutMe("Like animals")
            .registrationDate(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .build();

    public final static UserInfoDto USER_INFO_DTO = UserInfoDto.builder()
            .id(1L)
            .name("John Doe")
            .age(22)
            .email("example.example@example.com")
            .aboutMe("Like animals")
            .registrationDate(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .build();

    public final static UserInfoDto UPDATED_USER_INFO_DTO = UserInfoDto.builder()
            .id(1L)
            .name("Jane Doe")
            .age(22)
            .email("example.example@example.com")
            .aboutMe("Like fish")
            .registrationDate(LocalDateTime.of(2022, 3, 22, 0, 0, 0))
            .build();

   // public final static UpdateUserDto UPDATE_USER_DTO
}
