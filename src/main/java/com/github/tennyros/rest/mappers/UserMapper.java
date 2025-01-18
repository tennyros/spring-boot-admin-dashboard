package com.github.tennyros.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.github.tennyros.rest.dtos.UserRequestDto;
import com.github.tennyros.rest.dtos.UserResponseDto;
import com.github.tennyros.rest.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User requestToEntity(UserRequestDto userRequestDto);
    @Mapping(target = "roles", ignore = true)
    void updateUserFromDto(UserRequestDto userRequestDto, @MappingTarget User user);

    @Mapping(target = "password", ignore = true)
    User responseToEntity(UserResponseDto userResponseDto);
    UserResponseDto toResponseDto(User user);
}