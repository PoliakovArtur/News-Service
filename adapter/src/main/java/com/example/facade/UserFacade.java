package com.example.facade;

import com.example.dto.user.request.UpdateUserRequest;
import com.example.dto.user.response.UserResponse;
import com.example.filter.impl.UserFilter;
import com.example.dto.user.request.CreateUserRequest;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.example.utils.annotations.Facade;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Facade
@RequiredArgsConstructor
public class UserFacade {

    private final UserMapper mapper;
    private final UserService service;

    public List<UserResponse> findAll(UserFilter filter) {
        return mapper.toDtoList(service.findAll(filter));
    }

    public UserResponse findById(Long id) {
        return mapper.toDto(service.findById(id));
    }

    public UserResponse save(CreateUserRequest dto) {
        return mapper.toDto(service.save(mapper.fromDto(dto)));
    }

    public UserResponse updateById(Long id, UpdateUserRequest dto) {
        return mapper.toDto(service.updateById(id, mapper.fromDto(dto)));
    }

    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
