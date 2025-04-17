package com.github.tennyros.dashboard.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.github.tennyros.dashboard.dtos.UserRequestDto;
import com.github.tennyros.dashboard.dtos.UserResponseDto;
import com.github.tennyros.dashboard.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User requestToEntity(UserRequestDto userRequestDto);
    @Mapping(target = "roles", ignore = true)
    void updateUserFromDto(UserRequestDto userRequestDto, @MappingTarget User user);
    
    UserResponseDto toResponseDto(User user);
}