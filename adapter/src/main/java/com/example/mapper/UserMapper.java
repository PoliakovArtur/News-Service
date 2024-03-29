package com.example.mapper;

import com.example.dto.user.request.CreateUserDto;
import com.example.dto.user.request.UpdateUserDto;
import com.example.dto.user.response.UserInfoDto;
import com.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User fromDto(CreateUserDto dto);

    User fromDto(UpdateUserDto dto);

    UserInfoDto toDto(User user);

    List<UserInfoDto> toDtoList(Page<User> userPage);
}
