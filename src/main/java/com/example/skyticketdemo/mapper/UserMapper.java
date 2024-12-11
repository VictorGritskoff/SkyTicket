package com.example.skyticketdemo.mapper;

import com.example.skyticketdemo.dto.UserCreateDTO;
import com.example.skyticketdemo.dto.UserDTO;
import com.example.skyticketdemo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    UserDTO toDto(User user);

    @Mapping(target = "userID", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntity(UserCreateDTO userDTO);
}
