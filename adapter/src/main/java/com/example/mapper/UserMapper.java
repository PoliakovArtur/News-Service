package com.example.mapper;

import com.example.dto.user.request.CreateUserRequest;
import com.example.dto.user.request.UpdateUserRequest;
import com.example.dto.user.response.UserResponse;
import com.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User fromDto(CreateUserRequest dto);

    User fromDto(UpdateUserRequest dto);

    UserResponse toDto(User user);

    List<UserResponse> toDtoList(Iterable<User> iterable);
}
