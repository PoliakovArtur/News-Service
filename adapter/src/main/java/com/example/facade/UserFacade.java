package com.example.facade;

import com.example.dto.user.request.UpdateUserDto;
import com.example.filter.impl.UserFilter;
import com.example.dto.user.request.CreateUserDto;
import com.example.dto.user.response.UserInfoDto;
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

    public List<UserInfoDto> findAll(UserFilter filter) {
        return mapper.toDtoList(service.findAll(filter));
    }

    public UserInfoDto findById(Long id) {
        return mapper.toDto(service.findById(id));
    }

    public UserInfoDto save(CreateUserDto dto) {
        return mapper.toDto(service.save(mapper.fromDto(dto)));
    }

    public UserInfoDto updateById(Long id, UpdateUserDto dto) {
        return mapper.toDto(service.updateById(id, mapper.fromDto(dto)));
    }

    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
