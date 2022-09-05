package com.rms.assignment.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.rms.assignment.api.dto.UserDto;
import com.rms.assignment.core.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserDtoMapper {

    UserDto toUserDto(User user);

    User toUser(UserDto userDto);

}
